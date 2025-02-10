package com.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_api")
public class UserApi implements Serializable {
    @Id
    private String id;
    private String apiId;
    private String userApiId;
    private String apiName;
    private String requestTime;
    private String status;
    private int statusCode;
    private String reason;

}
