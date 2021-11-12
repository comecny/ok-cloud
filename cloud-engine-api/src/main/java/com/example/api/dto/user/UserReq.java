package com.example.api.dto.user;

import com.example.api.dto.HeadReq;

public class UserReq extends HeadReq {

    private static final long serialVersionUID = -5234974185848588766L;

    private Integer id;
    private String name;
    private String age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
