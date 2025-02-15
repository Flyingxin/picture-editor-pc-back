package com.entity.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class VipChargeRecord implements Serializable {
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

}
