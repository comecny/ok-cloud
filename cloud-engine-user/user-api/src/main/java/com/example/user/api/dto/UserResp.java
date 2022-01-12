package com.example.user.api.dto;

import com.example.core.common.HeadResp;
import lombok.Data;

import java.util.List;

@Data
public class UserResp extends HeadResp {

    private static final long serialVersionUID = -4050547271749931247L;

    List<UserRespInner> userRespInnerList;

    @Data
    public static class UserRespInner{
        private Integer id;
        private String name;
        private String age;
    }
}
