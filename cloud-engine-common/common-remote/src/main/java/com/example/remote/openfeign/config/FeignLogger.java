package com.example.remote.openfeign.config;

import feign.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义日志过滤
 */
@Configuration
public class FeignLogger extends feign.Logger {

    public final Logger logger;

    public FeignLogger(Logger logger) {
        this.logger = logger;
    }
    @Override
    protected void log(String configKey, String format, Object... args) {
        System.out.println("");
    }

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    public static class FeignCustomLoggerFactory implements FeignLoggerFactory {

        @Override
        public Logger create(Class<?> type) {
            return new FeignLogger((Logger) LoggerFactory.getLogger(type));
        }
    }

}
