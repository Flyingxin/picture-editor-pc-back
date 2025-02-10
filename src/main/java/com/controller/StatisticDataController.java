package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statisticData")
public class StatisticDataController {
    @Autowired
    JdbcTemplate jdbc;

    // 定时统计每日数据量
    @RequestMapping("/dayAmount")
    public void dayAmount() {

        try {
            // 查询每日登录数据量
            String loginAmountSql = "SELECT COUNT(*) FROM user_info WHERE DATE(last_login_time) = CURDATE()";
            Integer loginAmount = jdbc.queryForObject(loginAmountSql, Integer.class);

            // 查询每日注册数据量
            String registerAmountSql = "SELECT COUNT(*) FROM user_info WHERE DATE(last_login_time) = CURDATE()";
            Integer registerAmount = jdbc.queryForObject(registerAmountSql, Integer.class);

            // 每日注册数据量和每日登录数量加上今日日期插入statistic_day_amount表中
            String insertSql = "INSERT INTO statistic_day_amount (date, loginAmount, registerAmount) VALUES (CURDATE(), ?, ?)";
            jdbc.update(insertSql, loginAmount, registerAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 定时统计每日vip收入和购买次数
    @RequestMapping("/dayVip")
    public void dayVip() {
        try {
            // 查询每日vip总收入
            String vipIncomeSql = "SELECT SUM(price) FROM vip_charge_record WHERE DATE(payTime) = CURDATE()";
            Integer vipIncome = jdbc.queryForObject(vipIncomeSql, Integer.class);

            // 查询每日vip购买次数
            String vipBuyAmountSql = "SELECT COUNT(*) FROM vip_charge_record WHERE DATE(payTime) = CURDATE()";
            Integer vipBuyAmount = jdbc.queryForObject(vipBuyAmountSql, Integer.class);
            System.out.println(vipBuyAmount);
            System.out.println(vipIncome);
            // 每日vip总收入和购买次数加上今日日期插入statistic_day_vip表中
            String insertSql = "INSERT INTO statistic_day_vip (date, totalPrice, totalPurchase) VALUES (CURDATE(), ?, ?)";
            jdbc.update(insertSql, vipIncome, vipBuyAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 定时统计AI接口调用成功与失败数
    @RequestMapping("/dayApi")
    public String dayApi() {
        try {
            // 成功数
            String successSql = "SELECT COUNT(*) FROM user_api WHERE DATE(requestTime) = CURDATE() AND status = ? and apiName= ?";
            Integer successMigrate = jdbc.queryForObject(successSql,new Object[]{"true","migrate"}, Integer.class);

            successSql = "SELECT COUNT(*) FROM user_api WHERE DATE(requestTime) = CURDATE() AND status = ? and apiName= ?";
            Integer successEnhance = jdbc.queryForObject(successSql,new Object[]{"true","enhance"}, Integer.class);


            // 失败数
            String failSql = "SELECT COUNT(*) FROM user_api WHERE DATE(requestTime) = CURDATE() AND status = ? and apiName= ?";
            Integer failMigrate = jdbc.queryForObject(failSql,new Object[]{"false","migrate"}, Integer.class);

            failSql = "SELECT COUNT(*) FROM user_api WHERE DATE(requestTime) = CURDATE() AND status = ? and apiName= ?";
            Integer failEnhance= jdbc.queryForObject(failSql,new Object[]{"false","enhance"}, Integer.class);

            // 每日AI接口调用成功数和失败数加上今日日期插入statistic_day_api表中
            String insertSql = "INSERT INTO statistic_day_api (date,successMigrate, failMigrate,successEnhance,failEnhance) VALUES (CURDATE(), ?, ?, ?, ?)";
            jdbc.update(insertSql, successMigrate, failMigrate,successEnhance, failEnhance);

            return "统计成功";
        } catch (DataAccessException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
