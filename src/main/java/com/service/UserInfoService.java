package com.service;

import com.common.ApiResponse;
import com.entity.user.UserInfo;

/**
 * 用户信息服务
 */
public interface UserInfoService {

    ApiResponse<UserInfo> login(String telephone, String password);

    ApiResponse<String> register(UserInfo userInfo);
}
