package com.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account_froze")
public class AccountFroze implements Serializable {
    @Id
    private String id;
    private String frozeId;
    private String frozeType;
    private String frozeReason;
    private String startTime;
    private String endTime;
}
