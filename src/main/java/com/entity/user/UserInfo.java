package com.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

// 用户信息表 user_info
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) // 自动省略为null的字段
public class UserInfo implements Serializable {

    private Long id;

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

    private LocalDateTime updateTime;

}