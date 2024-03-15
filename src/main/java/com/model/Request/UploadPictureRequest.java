package com.model.Request;

public class UploadPictureRequest {
    private  String openId;
    private String pictureId;
    // base64
    private  String pictureUrl;
    // 更新时间
    private  String updateTime;
    // 图片类型
    private String type;


    public String getOpenId() {
        return openId;
    }
    public String getPictureId() { return pictureId; }
    public String getPictureUrl() { return pictureUrl; }
    public String getUpdateTime() {
        return updateTime;
    }
    public String getType() {
        return type;
    }
}
