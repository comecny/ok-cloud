package com.redis.util;

import com.redis.base.Flag;

public interface RedisKey {

    /**测试*/
    String HOT_KEY = generateKey("test", "hot");


    static String generateKey(Object... args) {
        StringBuffer sbr = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            if(i>0) {
                sbr.append(Flag.KEY_SEPARATOR);
            }
            sbr.append(args[i]);
        }
        return sbr.toString();
    }

    static String generateKey(Flag flag, Object... args) {
        StringBuffer sbr = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            if(i>0) {
                sbr.append(Flag.KEY_SEPARATOR);
            }
            sbr.append(args[i]);
        }
        return sbr.toString();
    }

}
