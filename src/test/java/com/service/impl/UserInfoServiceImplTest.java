package com.service.impl;

import com.dto.CommonAuthInfoDto;
import com.entity.user.AccountFroze;
import com.entity.user.UserInfo;
import com.mapper.user.UserInfoMapper;
import com.response.BaseResponse;
import com.utils.Date;
import com.utils.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoServiceImplTest {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    void login() {

        // 当前时间与冻结时间
        LocalDateTime currentDateTime = Date.stringToLocalDateTime(Date.getCurrentDateTime());
        try {
            // 修改最新登录时间
            userInfoMapper.freshLoginTime("a28cxmenud1712908566457", currentDateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(123);


    }
}