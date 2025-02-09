package com.service;

import com.dto.CommonAuthInfoDto;
import com.dto.AuthResultDto;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户身份验证服务
 */
public interface AuthService {
	/**
	 * 通用校验方式
	 */
	AuthResultDto<CommonAuthInfoDto> commonAuth(HttpServletRequest request);

}
