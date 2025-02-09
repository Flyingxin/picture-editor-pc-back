package com.entity.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPicture implements Serializable {
    private String id;
    private  String userPictureId;
    private String pictureId;
    // base64
    private  String pictureUrl;
    private  String createTime;
    // 更新时间
    private  String updateTime;
    // 图片类型
    private String pictureType;
    private String  pictureSuffix;

}
