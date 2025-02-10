package com.entity.manager;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "statistic_day_vip")
public class StatisticDayVip implements Serializable {
    @Id
    private String id;
    private String date;
    private String totalPrice;
    private String totalPurchase;

}
