package com.service;

import com.response.BaseResponse;
import com.entity.user.UserInfo;

/**
 * 用户信息服务
 */
public interface UserInfoService {

    BaseResponse<UserInfo> login(String telephone, String password);

    BaseResponse<String> register(UserInfo userInfo);
}
