package com.controller;
import com.common.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据图表
 * 1. 期间日注册人数
 * 2. 期间日登录人数
 * 3. 期间日vip总收入
 * 4. 图片融合期间日调用次数
 * @date 2024/3/8
 * @author ccyx
 * @description 接口测试完毕、
 * @defect 未接入日志系统
 */

@RestController
@RequestMapping("/dataCharts")
public class DataCharts {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询user_info表期间的注册和登录人数
    @GetMapping("/dayAmount")
    public ApiResponse<List<Map<String, Object>>> dayAmount(@RequestParam String startDate, @RequestParam String endDate){
        if (startDate == "" && endDate == "") return ApiResponse.error(400,"请求参数错误");

        try {
            String sql = "select * from statistic_day_amount where date between ? and ? order by date asc";
//            String sql="select DATE(createTime) as date, count(*) as count from user_info where DATE(createTime) between ? and ? group by DATE(createTime)";
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, new Object[]{startDate, endDate});
            return ApiResponse.success(results,"查询成功");
        }catch (DataAccessException e){
            System.out.println("DataAccessException: " + e.getMessage());
            return ApiResponse.error(500,"服务器崩溃");
        }
    }


    // 查询vip_total_income表某天的总收入
    @GetMapping("/dayVip")
    public ApiResponse<List<Map<String, Object>>> dayVip(@RequestParam String startDate, @RequestParam String endDate){
        if (startDate == "" && endDate == "") return ApiResponse.error(400,"请求参数错误");
        try {
            String sql = "select * from statistic_day_vip where date between ? and ? order by date asc";
//            String sql="select DATE(createTime) as date, sum(money) as sum from vip_total_income where DATE(createTime) between ? and ? group by DATE(createTime)";
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, new Object[]{startDate, endDate});
            return ApiResponse.success(results,"查询成功");
        }catch (DataAccessException e){
            System.out.println("DataAccessException: " + e.getMessage());
            return ApiResponse.error(500,"服务器崩溃");
        }
    }

    // 查询stable_diffusion_picture表某天的总调用次数
    @GetMapping("/dayApi")
    public ApiResponse<List<Map<String, Object>>> dayApi(@RequestParam String startDate, @RequestParam String endDate){
        if (startDate == "" && endDate == "") return ApiResponse.error(400,"请求参数错误");

        try {
            String sql = "select * from statistic_day_api where date between ? and ?  order by date asc";
            // String sql="select DATE(requestTime) as date, count(*) as count from stable_diffusion_api where DATE(requestTime) between ? and ? group by DATE(requestTime)";
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, new Object[]{startDate, endDate});
            return ApiResponse.success(results,"查询成功");
        }catch (DataAccessException e){
            System.out.println("DataAccessException: " + e.getMessage());
            return ApiResponse.error(500,"服务器崩溃了");
        }
    }
}
