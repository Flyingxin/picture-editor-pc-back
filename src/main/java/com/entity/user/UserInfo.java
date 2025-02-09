package com.entity.user;

import lombok.Data;

import java.io.Serializable;

// 用户信息表 user_info
@Data
public class UserInfo implements Serializable {

    private String id;

    private String openId;

    private String avatar;

    private String telephone;

    private String frozeId;

    private String nickName;

    private String password;

    private String name;

    private String sex;

    private String idCard;

    private String birthday;

    private String email;

    private String identity;

    private String createTime;

    private String lastLoginTime;

    private String token;

}