package com.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account_vip_info")
public class AccountVipInfo implements Serializable {
    @Id
    private String id;
//    private String orderId;
//    private String openId;
    private String userVipId;
    private String createTime;
    private String endTime;
    private boolean isForever;
    private String gradeScore;
//    private String price;
//    private String unit;

}
