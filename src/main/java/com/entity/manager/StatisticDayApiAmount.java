package com.entity.manager;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "statistic_day_api_amount")
public class StatisticDayApiAmount implements Serializable {
    @Id
    private String id;
    private String date;
    private String apiName;
    private String successAmount;
    private String failAmount;
}
