package com.model.dto.user;

public class AccountBanDTO {
    private String id;
    private String openId;
    private String banReason;
    private String startTime;
    private String endTime;

    public String getId() { return id; }

    public String getOpenId() {
        return openId;
    }

    public String getBanReason() {
        return banReason;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
