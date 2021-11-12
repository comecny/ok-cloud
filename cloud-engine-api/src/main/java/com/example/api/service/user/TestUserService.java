package com.example.api.service.user;

import com.example.api.dto.user.UserReq;
import com.example.api.dto.user.UserResp;
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
