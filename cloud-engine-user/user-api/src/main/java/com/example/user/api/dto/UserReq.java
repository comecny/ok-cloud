package com.example.user.api.dto;

import com.example.core.common.HeadReq;
import lombok.Data;

@Data
public class UserReq extends HeadReq {

    private static final long serialVersionUID = -5234974185848588766L;

    private Integer id;
    private String name;
    private String age;

}
