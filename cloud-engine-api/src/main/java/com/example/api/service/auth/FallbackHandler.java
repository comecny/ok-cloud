package com.example.api.service.auth;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FallbackHandler implements FallbackFactory {

    @Override
    public Object create(Throwable throwable) {
        System.out.println("1");
        return null;
    }
}
