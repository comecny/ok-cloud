package com.example.user.api.service;

import com.example.user.api.dto.UserReq;
import com.example.user.api.dto.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${openfeign.send.user}")
public interface TestUserService {

    /**
     * 用户测试类
     * @param req
     * @return
     */
    @RequestMapping(value = "/testUser" ,method = RequestMethod.GET)
    UserResp testUser(UserReq req);
}
