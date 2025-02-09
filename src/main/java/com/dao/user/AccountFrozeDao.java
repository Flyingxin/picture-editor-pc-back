package com.dao.user;

import com.entity.user.AccountFroze;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * (account_froze)表数据库访问层
 * @author ccyx
 * date 2026-02-08 12:03:00
 */
@Mapper
@Repository
public interface AccountFrozeDao {

    // 查询用户是否封禁
    AccountFroze queryUserForFroze(@Param("frozeId") String frozeId);
}
