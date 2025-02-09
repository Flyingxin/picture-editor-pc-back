package com.entity.manager;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticDayVip implements Serializable {
    private String id;
    private String date;
    private String totalPrice;
    private String totalPurchase;

}
