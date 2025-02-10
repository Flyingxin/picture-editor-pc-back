package com.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

// 用户信息表 user_info
@Data
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

    @Id
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