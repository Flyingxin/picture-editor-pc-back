package com.entity.manager;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticDayAmount implements Serializable {
    private String id;
    private String loginDate;
    private String loginAmount;
    private String registerAmount;

}
