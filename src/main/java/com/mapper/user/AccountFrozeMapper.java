package com.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface AccountFrozeMapper extends BaseMapper<AccountFroze> {

    // 查询用户是否封禁
    AccountFroze queryUserForFroze(@Param("frozeId") String frozeId);

//    AccountFroze searchByFrozeId(String frozeId);
}
