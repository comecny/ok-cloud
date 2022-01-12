package com.example.remote.openfeign.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * 编码器，处理请求
 */
public class FeignEncoder implements Encoder {

    private static final Logger logger = LoggerFactory.getLogger(FeignDecoder.class);

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        logger.info("打印请求 ！ ");
    }
}
