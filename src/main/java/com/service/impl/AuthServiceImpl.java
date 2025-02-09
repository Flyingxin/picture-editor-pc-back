package com.service.impl;

import com.dto.CommonAuthInfoDto;
import com.dto.AuthResultDto;
import com.service.AuthService;
import com.utils.jwt.JwtUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {

    // token请求头
    private static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

    /**
     * 通用校验方式
     */
    @Override
    public AuthResultDto<CommonAuthInfoDto> commonAuth(HttpServletRequest request) {

        String authorization = request.getHeader(HTTP_HEADER_AUTHORIZATION);
        if (authorization == null || authorization.isEmpty()) {
            return AuthResultDto.fail("验证失败0");
        }

        try {
            CommonAuthInfoDto tokenInfo = JwtUtil.parseToken(authorization, CommonAuthInfoDto.class);

            if (tokenInfo.getApp() == null || !tokenInfo.getApp().equals("pictureEditor")) {
                return AuthResultDto.fail("验证失败1");
            }
            return AuthResultDto.success();

        } catch (Exception e) {
            return AuthResultDto.fail("身份验证异常");
        }
    }


}
