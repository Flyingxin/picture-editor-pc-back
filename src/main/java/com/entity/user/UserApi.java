package com.entity.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserApi implements Serializable {
    private String id;
    private String apiId;
    private String userApiId;
    private String apiName;
    private String requestTime;
    private String status;
    private int statusCode;
    private String reason;

}
