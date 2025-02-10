package com.entity.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountVipInfo implements Serializable {
    private String id;
    private String userVipId;
    private String createTime;
    private String endTime;
    private boolean isForever;
    private String gradeScore;

}
