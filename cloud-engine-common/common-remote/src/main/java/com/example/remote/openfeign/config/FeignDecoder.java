package com.example.remote.openfeign.config;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 解码器 处理响应结果
 */
public class FeignDecoder implements Decoder {

    private static final Logger logger = LoggerFactory.getLogger(FeignDecoder.class);
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        logger.info("fegin recv");
        return  Util.toString( response.body().asReader(Util.UTF_8));
    }
}
