package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statisticData")
public class StatisticData {
    @Autowired
    JdbcTemplate jdbc;

    // 定时统计每日数据量
    @RequestMapping("/dayAmount")
    public void dayAmount() {

        try {
            // 查询每日登录数据量
            String loginAmountSql = "SELECT COUNT(*) FROM user_info WHERE DATE(lastLoginTime) = CURDATE()";
            Integer loginAmount = jdbc.queryForObject(loginAmountSql, Integer.class);

            // 查询每日注册数据量
            String registerAmountSql = "SELECT COUNT(*) FROM user_info WHERE DATE(createTime) = CURDATE()";
            Integer registerAmount = jdbc.queryForObject(registerAmountSql, Integer.class);

            // 每日注册数据量和每日登录数量加上今日日期插入statistic_day_amount表中
            String insertSql = "INSERT INTO statistic_day_amount (date, loginAmount, registerAmount) VALUES (CURDATE(), ?, ?)";
            jdbc.update(insertSql, loginAmount, registerAmount);

            // 打印或存储结果
            System.out.println("Login amount: " + loginAmount);
            System.out.println("Register amount: " + registerAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
