package com.model.dto.user;

public class StableDiffusionPictureDTO {
    private String id;
    private String pictureId;
    private String openId;
    private String pictureUrl;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }
    public String getPictureId() { return pictureId; }

    public String getOpenId() {
        return openId;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
    public String getCreateTime() {
        return createTime;
    }
    public String getUpdateTime() {
        return updateTime;
    }
}
