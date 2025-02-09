package com.dao.user;

import com.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * (user_info)表数据库访问层
 * author ccyx
 * date 2026-02-08 12:03:00
 */
@Mapper // 告诉springboot这是一个mybatis的mapper类
@Repository // 将userInfoDao交由spring容器管理
public interface UserInfoDao {

    //查询用户信息
    UserInfo queryUser(@Param("telephone") String telephone);

    // 更新用户登录时间
    Boolean freshLoginTime(
            @Param("openId") String openId,
            @Param("lastLoginTime") LocalDateTime lastLoginTime
    );

    // 添加用户
    int addUser(UserInfo userInfo);
}
