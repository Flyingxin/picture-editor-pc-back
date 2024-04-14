package com.model.dto.user;

public class VipChargeRecordDTO {
    private String id;
    private String chargeId;
    private String userVipId;
    private String payTime;
    private String endTime;
    private Float price;
    private String unit;
    private int score;
    private String payType;
    private int renewDays;
    private String operatingUser;

    public String getId() { return id; }
    public String getChargeId() { return chargeId; }
    public String getUserVipId() { return userVipId; }
    public String getPayTime() { return payTime; }
    public String getEndTime() { return endTime; }
    public Float getPrice() { return price; }
    public String getUnit() { return unit; }
    public int getScore() { return score; }
    public String getPayType() { return payType; }
    public int getRenewDays() { return renewDays; }
    public String getOperatingUser() { return operatingUser; }

}
