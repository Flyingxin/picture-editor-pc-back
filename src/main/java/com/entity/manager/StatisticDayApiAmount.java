package com.entity.manager;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticDayApiAmount implements Serializable {
    private String id;
    private String date;
    private String apiName;
    private String successAmount;
    private String failAmount;
}
