package com.example.user.api.dto;

import com.example.core.common.HeadResp;

import java.util.List;

public class UserResp extends HeadResp {

    private static final long serialVersionUID = -4050547271749931247L;

    List<UserRespInner> userRespInnerList;

    public List<UserRespInner> getUserRespInnerList() {
        return userRespInnerList;
    }

    public void setUserRespInnerList(List<UserRespInner> userRespInnerList) {
        this.userRespInnerList = userRespInnerList;
    }

    public static class UserRespInner{
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


}
