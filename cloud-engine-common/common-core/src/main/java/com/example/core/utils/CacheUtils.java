package com.example.core.utils;

import com.example.core.dict.ErrorDict;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CacheUtils {

    private static final int MAX_CACHE_SIZE = 5_0000;

    private static Cache<String, Object> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.DAYS)
            .initialCapacity(50)
            .maximumSize(MAX_CACHE_SIZE)
            .build();

    public static Object getKey(String key) {
        return cache.getIfPresent(key);
    }

    public static void setKey(String key, Object value) {
        long size = cache.size();
        if (size >= MAX_CACHE_SIZE) {
            throw new RuntimeException("local cache overflow (10000.)");
        }
        cache.put(key, value);
    }

    public static void removeKey(String key) {
        cache.invalidate(key);
    }

    public static void clearAll() {
        cache.invalidateAll();
    }

    public static ConcurrentMap<String, Object> queryAll() {
        return cache.asMap();
    }
}
