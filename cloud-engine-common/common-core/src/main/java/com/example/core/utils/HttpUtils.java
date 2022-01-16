package com.example.core.utils;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;

/**
 * RestTemplate 封装http请求工具类
 */
public class HttpUtils {

    static final ConcurrentHashMap<String, RestTemplate> RESTTEMPLATE_CACHE = new ConcurrentHashMap<String, RestTemplate>();

    public void doGet(String url){

    }
}
