package com.example.core.id;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class SnowflakeIdWorker {

	// ==============================Fields===========================================
	/** 开始时间截 (2015-01-01) */
	private final long twepoch = 1451577600000L;

	/** 机器id所占的位数 */
	private final long workerIdBits = 5L;

	/** 数据标识id所占的位数 */
	private final long datacenterIdBits = 5L;

	/** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

	/** 支持的最大数据标识id，结果是31 */
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

	/** 序列在id中占的位数 */
	private final long sequenceBits = 12L;

	/** 机器ID向左移12位 */
	private final long workerIdShift = sequenceBits;

	/** 数据标识id向左移17位(12+5) */
	private final long datacenterIdShift = sequenceBits + workerIdBits;

	/** 时间截向左移22位(5+5+12) */
	private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	/** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	/** 工作机器ID(0~31) */
	private long workerId;

	public long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}

	public long getDatacenterId() {
		return datacenterId;
	}

	public void setDatacenterId(long datacenterId) {
		this.datacenterId = datacenterId;
	}

	/** 数据中心ID(0~31) */
	private long datacenterId;

	/** 毫秒内序列(0~4095) */
	private long sequence = 0L;

	/** 上次生成ID的时间截 */
	private long lastTimestamp = -1L;
	
    public SnowflakeIdWorker() {
        this.datacenterId = getDatacenterId(maxDatacenterId);
        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
    }

    /**
     * @param workerId     工作机器ID
     * @param datacenterId 序列号
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

	/**
	 * 获得下一个ID (该方法是线程安全的)
	 * 
	 * @return SnowflakeId
	 */
	public synchronized Object generate() {
		long timestamp = timeGen();

		// 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if (timestamp < lastTimestamp) {
			/*throw new PeRuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));*/
		}

		// 如果是同一时间生成的，则进行毫秒内序列
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			// 毫秒内序列溢出
			if (sequence == 0) {
				// 阻塞到下一个毫秒,获得新的时间戳
				timestamp = tilNextMillis(lastTimestamp);
			}
		}
		// 时间戳改变，毫秒内序列重置
		else {
			sequence = 0L;
		}

		// 上次生成ID的时间截
		lastTimestamp = timestamp;

		// 移位并通过或运算拼到一起组成64位的ID
		return ((timestamp - twepoch) << timestampLeftShift) //
				| (datacenterId << datacenterIdShift) //
				| (workerId << workerIdShift) //
				| sequence;
	}

	/**
	 * 阻塞到下一个毫秒，直到获得新的时间戳
	 * 
	 * @param lastTimestamp
	 *            上次生成ID的时间截
	 * @return 当前时间戳
	 */
	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * 返回以毫秒为单位的当前时间
	 * 
	 * @return 当前时间(毫秒)
	 */
	protected long timeGen() {
		return System.currentTimeMillis();
	}
	
    /**
     * <p>
     * 数据标识id部分
     * </p>
     */
    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if(mac!=null) {
                    id = ((0x000000FF & (long) mac[mac.length - 1])
                            | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                } else {
                    id = 1L;
                }

            }
        } catch (Exception e) {
        	//throw new PeRuntimeException(e);
        }
        return id;
    }
    
    /**
     * <p>
     * 获取 maxWorkerId
     * </p>
     */
    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            /*
             * GET jvmPid
             */
            mpid.append(name.split("@")[0]);
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }
    
    
    
    public static void testProductIdByMoreThread(int dataCenterId, int workerId, final int n) throws InterruptedException {  
        List<Thread> tlist = new ArrayList<Thread>();  
        final Set<Long> setAll = new HashSet<Long>();  
        final CountDownLatch cdLatch = new CountDownLatch(10);  
        long start = System.currentTimeMillis();  
        int threadNo = dataCenterId;  
        final Map<String,SnowflakeIdWorker> idFactories = new HashMap<String,SnowflakeIdWorker>();  
        for(int i=0;i<10;i++){  
            //用线程名称做map key.  
            idFactories.put("snowflake"+i,new SnowflakeIdWorker(workerId, threadNo++));  
        }  
        for(int i=0;i<10;i++){  
            Thread temp =new Thread(new Runnable() {  
                @Override  
                public void run() {  
                    Set<Long> setId = new HashSet<Long>();  
                    SnowflakeIdWorker idWorker = idFactories.get(Thread.currentThread().getName());  
                    for(int j=0;j<n;j++){
                    	Long id=(Long)idWorker.generate();
                    	System.err.println(id);
                        setId.add(id);  
                    }  
                    synchronized (setAll){  
                        setAll.addAll(setId);  
                    }  
                    cdLatch.countDown();  
                }  
            },"snowflake"+i);  
            tlist.add(temp);  
        }  
        for(int j=0;j<10;j++){  
            tlist.get(j).start();  
        }  
        cdLatch.await();  
  
        long end1 = System.currentTimeMillis() - start;  
  
       System.err.println("共耗时:{"+end1+"}毫秒,预期应该生产{"+10*n+"}个id, 实际合并总计生成ID个数:{"+setAll.size()+"}");  
  
    }  
  
  
    public static void main(String[] args) {
        /** case1: 测试每秒生产id个数? 
         *   结论: 每秒生产id个数300w+ */  
        //testPerSecondProductIdNums();  
  
        /** case2: 单线程-测试多个生产者同时生产N个id,验证id是否有重复? 
         *   结论: 验证通过,没有重复. */  
        //testProductId(1,2,10000);//验证通过!  
        //testProductId(1,2,20000);//验证通过!  
  
        /** case3: 多线程-测试多个生产者同时生产N个id, 全部id在全局范围内是否会重复? 
         *   结论: 验证通过,没有重复. */
    }  
}
