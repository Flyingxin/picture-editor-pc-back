package com.model.dto.user;

public class UserPictureDTO {
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


    public String getId() {return id;}
    public String getUserPictureId() {
        return userPictureId;
    }
    public String getPictureId() { return pictureId; }
    public String getPictureUrl() { return pictureUrl; }
    public String getCreateTime() { return createTime; }
    public String getUpdateTime() {
        return updateTime;
    }
    public String getPictureType() {
        return pictureType;
    }
    public String getPictureSuffix() {
        return pictureSuffix;
    }

}
