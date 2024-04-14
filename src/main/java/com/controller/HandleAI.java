package com.controller;

import com.common.ApiResponse;
import com.model.dto.user.UserApiDTO;
import com.utils.GenerateString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/handleAI")
public class HandleAI {
    @Autowired
    JdbcTemplate jdbc;

    // 记录AI接口调用成功与失败数
    @RequestMapping("/recordAiApi")
    public ApiResponse<String> recordAiApi(@RequestBody UserApiDTO data){
        String apiId = data.getApiId();
        String userApiId = data.getUserApiId();
        String apiName = data.getApiName();
        String status = data.getStatus();
        int statusCode = data.getStatusCode();
        String reason = data.getReason();

        String requestTime = GenerateString.getCurrentDateTime();
        try {
            String sql = "INSERT INTO user_api (apiId, userApiId, apiName, requestTime, status, statusCode, reason) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbc.update(sql, apiId, userApiId, apiName, requestTime, status, statusCode, reason);
            return ApiResponse.success(null, "记录成功");
        } catch (DataAccessException  e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }
}
