package com.example.api.service.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${openfeign.send.auth}")
public interface TestAuthService {

    @RequestMapping(value = "/testAuth" ,method = RequestMethod.GET)
    String testAuth();
}
