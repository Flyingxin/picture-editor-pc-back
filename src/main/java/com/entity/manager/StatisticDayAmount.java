package com.entity.manager;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "statistic_day_amount")
public class StatisticDayAmount implements Serializable {
    @Id
    private String id;
    private String loginDate;
    private String loginAmount;
    private String registerAmount;

}
