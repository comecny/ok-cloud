package com.redis.base;

public interface Flag {
    /**缓存key开始标识*/
    String START_FLAG = "__";
    String END_FLAG = "__";
    /**缓存key分隔符*/
    String KEY_SEPARATOR = ":";
    /**缓存字段分隔符*/
    String SEPARATOR = "@";
    /**匹配任意字符*/
    String ANY = "*";
    /**匹配任意字符*/
    String MATCH_ANY = ".*";
}
