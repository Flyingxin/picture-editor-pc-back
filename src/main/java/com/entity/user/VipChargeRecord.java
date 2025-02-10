package com.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "vip_charge_record")
public class VipChargeRecord implements Serializable {
    @Id
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
