package com.example.remote.openfeign.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDncoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return null;
    }
}
