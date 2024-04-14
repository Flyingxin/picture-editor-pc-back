package com.controller;

import com.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderFlow")
public class OrderFlow {
    @Autowired
    JdbcTemplate jdbc;

    // 查询vip_charge_record表的充值记录
    @RequestMapping("/getChargeRecord")
    public ApiResponse<List<Map<String, Object>>> getChargeRecord() {

        try {
            String sql = "select * from vip_charge_record";
            List<Map<String, Object>> results = jdbc.queryForList(sql);
            return ApiResponse.success(results, "查询成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }



}
