package com;

import com.mapper.user.UserInfoMapper;
import com.service.UserInfoService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest // 获取启动类，加载配置，寻找主配置启动类
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserInfoService userInfoService;


    @Test
    public void test() throws Exception {
        long aa = userInfoMapper.selectCount(null);
        System.out.println(aa);
    }
}
