package com.example.remote.openfeign.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
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
    RequestInterceptor customFeignRequestInterceptor(){
        return new FeignRequestInterceptor();
    }

    @Bean
    Decoder customFeignEncoder(){
        return new FeignDecoder();
    }

}
