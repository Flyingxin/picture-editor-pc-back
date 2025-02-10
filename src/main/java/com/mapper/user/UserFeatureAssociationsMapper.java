package com.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.user.UserFeatureAssociations;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserFeatureAssociationsMapper extends BaseMapper<UserFeatureAssociations> {

    // 添加用户功能映射id
    int addFunctionId(
            @Param("openId") String openId,
            @Param("userVipId") String userVipId,
            @Param("userPictureId") String userPictureId,
            @Param("userApiId") String userApiId
    );
}
