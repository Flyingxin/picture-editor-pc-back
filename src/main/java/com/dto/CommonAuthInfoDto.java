package com.dto;

import lombok.Data;

@Data
public class CommonAuthInfoDto {

    // 应用
    private String app;

    // userId
    private String userId;

    // 用户
    private String nick;

    // 角色
    private String role;

    // 时间戳
    private Long timestamp;

    // 静态验证密钥，不传timestamp时，用于开发人员验证
    private String devSecret;
}
