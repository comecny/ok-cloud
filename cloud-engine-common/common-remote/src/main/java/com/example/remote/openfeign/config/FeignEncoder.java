package com.example.remote.openfeign.config;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 解码器 处理响应结果
 */
@Configuration
public class FeignEncoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {

        return null;
    }
}
