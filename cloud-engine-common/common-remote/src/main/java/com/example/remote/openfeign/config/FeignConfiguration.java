package com.example.remote.openfeign.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign配置出口
 */
@Configuration
public class FeignConfiguration {

    /**
     * feign 日志输出
     * @return
     */
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    /**
     * feign 拦截器配置
     * @return
     */
    @Bean
    RequestInterceptor customFeignRequestInterceptor(){
        return new FeignRequestInterceptor();
    }

    /**
     * feign 编码器配置
     * @return
     */
    @Bean
    Encoder customFeignEncoder(){
        return new FeignEncoder();
    }

    /**
     * feign 解码器配置
     * @return
     */
    @Bean
    Decoder customFeignDncoder(){
        return new FeignDecoder();
    }

    /**
     * 异常解码器
     * @return
     */
    @Bean
    ErrorDecoder customFeignErrorDncoder(){
        return new FeignErrorDncoder();
    }

}
