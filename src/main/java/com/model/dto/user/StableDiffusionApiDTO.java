package com.model.dto.user;

public class StableDiffusionApiDTO {
    private String id;
    private String apiName;
    private String openId;
    private String requestTime;
    private String isSuccess;
    private String status;
    private String reason;

    public String getId() {
        return id;
    }
    public String getApiName() {
        return apiName;
    }

    public String getOpenId() {
        return openId;
    }
    public String getRequestTime() {
        return requestTime;
    }
    public String getIsSuccess() {
        return isSuccess;
    }
    public String getStatus() {
        return status;
    }
    public String getReason() {
        return reason;
    }
}
