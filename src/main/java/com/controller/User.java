package com.controller;

import com.common.ApiResponse;
import com.model.dto.user.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.jwt.JwtUtil.generateToken;

/**
 * 用户信息
 * 1. 用户登录
 * 2. 用户注册
 * 3. 用户注销（未）
 * @date 2024/3/8
 * @author ccyx
 * @description 接口测试完毕、
 * @defect 未接入日志系统
 * */

@RestController
@RequestMapping("/user")
public class User {
    @Autowired
    JdbcTemplate jdbc;

    // 查询user-info账户密码登录
    @GetMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestParam String telephone,@RequestParam String password){
        if (telephone == "" || password == "") {
            return ApiResponse.error(400, "账户或密码不能为空");
        }

        try {
            String sql="select * from user_info where telephone = ?";
            List<Map<String, Object>> results = jdbc.query(sql, new Object[]{telephone}, new ColumnMapRowMapper());
            if (results.isEmpty())  return ApiResponse.error(404, "账号不存在，请先注册");

            Map<String, Object> data = results.get(0);
            String storedPassword = data.get("password").toString();
            if (!password.equals(storedPassword))  return ApiResponse.error(204, "账号或密码错误");

            data.put("token", generateToken(data.get("openId").toString()));
            return ApiResponse.success(data,"查询成功");

        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃");
        }
    }

    // 注册用户
    @RequestMapping ("/register")
    public ApiResponse<String> register(@RequestBody UserInfoDTO data){
        String openId = data.getOpenId();
        String telephone = data.getTelephone();
        String password = data.getPassword();
        String nickName = data.getNickName();
        if (openId.isEmpty() || telephone.isEmpty() || password.isEmpty() || nickName.isEmpty()) {
            return ApiResponse.error(400, "请填写完整的信息");
        }

        try {
            String checkSql = "select count(*) from user_info where telephone = ?";
            int count = jdbc.queryForObject(checkSql, new Object[]{telephone}, Integer.class);
            if (count > 0) return ApiResponse.error(409, "用户已存在");

            String sql="insert into user_info(openId,telephone,password,nickName,isManager) values(?,?,?,?,?)";
            jdbc.update(sql,openId,telephone,password,nickName,0);
            return ApiResponse.success(null,"注册成功");

        } catch (DataAccessException e) {
            return ApiResponse.error(500, "Database error: " + e.getMessage());
        }
    }

    // 根据电话号码查询用户信息
    @RequestMapping("/getUserInfoByTelephone")
    public ApiResponse<Map<String, Object>> getUserInfoByTelephone(@RequestParam String telephone){
        if (telephone == "")
            return ApiResponse.error(400, "电话号码不能为空");
        try {
            String sql="select * from user_info where telephone = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, telephone);
            if (results.isEmpty()) return ApiResponse.error(404, "未查询到用户信息");
            if (results.size() > 1) return ApiResponse.error(500, "查询到多条用户信息");
            Map<String, Object> data = results.get(0);
            return ApiResponse.success(data,"查询成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 根据openId查询用户信息
    @RequestMapping("/getUserInfoByOpenId")
    public ApiResponse<Map<String, Object>> getUserInfoByOpenId(@RequestParam String openId){
        if (openId == "")
            return ApiResponse.error(400, "openId不能为空");
        try {
            String sql="select * from user_info where openId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, openId);
            if (results.isEmpty()) return ApiResponse.error(404, "未查询到用户信息");
            if (results.size() > 1) return ApiResponse.error(500, "查询到多条用户信息");
            Map<String, Object> data = results.get(0);
            return ApiResponse.success(data,"查询成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }
}
