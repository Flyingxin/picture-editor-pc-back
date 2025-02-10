package com.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
public class AccountFroze implements Serializable {
    private String id;
    private String frozeId;
    private String frozeType;
    private String frozeReason;
    private String startTime;
    private String endTime;
}
