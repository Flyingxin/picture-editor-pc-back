package com.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserFeatureAssociationsDao {

    // 添加用户功能映射id
    int addFunctionId(
            @Param("openId") String openId,
            @Param("userVipId") String userVipId,
            @Param("userPictureId") String userPictureId,
            @Param("userApiId") String userApiId
    );
}
