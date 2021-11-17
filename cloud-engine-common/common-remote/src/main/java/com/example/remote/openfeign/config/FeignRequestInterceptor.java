package com.example.remote.openfeign.config;

import feign.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求拦截
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(FeignRequestInterceptor.class);
    @Override
    public void apply(RequestTemplate template) {
        logger.info("feign send : ");
        Request request = template.request();
    }
}
