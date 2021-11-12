package com.example.core.id;

/**
 * 可提供多种id生成选择
 *
 */
public class IDGenerateFactory {

    private SnowflakeIdWorker snowflakeIdWorker;

    public Object generateId(){
         snowflakeIdWorker = new SnowflakeIdWorker();
        return snowflakeIdWorker.generate();
    }

    public void setSnowflakeIdWorker(SnowflakeIdWorker snowflakeIdWorker) {
       this.snowflakeIdWorker = snowflakeIdWorker;
    }
}
