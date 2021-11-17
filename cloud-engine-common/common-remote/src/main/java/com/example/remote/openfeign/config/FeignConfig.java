package com.example.remote.openfeign.config;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign配置出口
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    FeignLoggerFactory feignCustomLoggerFactory() {
        return new FeignLogger.FeignCustomLoggerFactory();
    }

}
