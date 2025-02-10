package com.entity.user;

import lombok.Data;


@Data
public class UserFeatureAssociations {
    private long id;
    private String openId;
    private String userVipId;
    private String userPictureId;
    private String userApiId;
}
