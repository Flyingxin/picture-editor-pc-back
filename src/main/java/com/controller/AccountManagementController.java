package com.controller;

import com.common.ApiResponse;
import com.entity.user.AccountFroze;
import com.entity.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accountManagement")
public class AccountManagementController {
    @Autowired
    JdbcTemplate jdbc;

    // 修改账号封禁(可以继续扩展为，封禁后，用户无法登录，但是可以查看自己的信息，但是无法进行任何操作，包括修改信息，删除信息等等)
    // 且数据库还可以扩展为，字段isBan添加到user_info中用来登录判断（有则查account_ban符合的记录放回），account_ban表允许openId有多条记录，用来后台行为分析
    // 利用frozeId,先查询account_ban表有数据就修改，没数据就插入
    @RequestMapping("/frozeUser")
    public ApiResponse<String> frozeUser(@RequestBody AccountFroze data){
        String frozeId = data.getFrozeId();
        String frozeType = data.getFrozeType();
        String frozeReason = data.getFrozeReason();
        String startTime = data.getStartTime();
        String endTime = data.getEndTime();
        if (frozeId.isEmpty() || frozeType.isEmpty() || frozeReason.isEmpty() || startTime.isEmpty() || endTime.isEmpty())
            return ApiResponse.error(400,"请求参数错误");

        try {
            String sql="select * from account_froze where frozeId = ?";
            List<Map<String, Object>> results = jdbc.query(sql, new Object[]{frozeId}, new ColumnMapRowMapper());
            if (results.size() == 0) {
                sql="insert into account_froze(frozeId,frozeType,frozeReason,startTime,endTime) values(?,?,?,?,?)";
                jdbc.update(sql, new Object[]{frozeId,frozeType, frozeReason, startTime, endTime});
                return ApiResponse.success(null,"封禁成功");
            } else if(results.size() == 1){
                String updateSql="update account_froze set frozeType=?,frozeReason=?,startTime=?,endTime=? where frozeId=?";
                jdbc.update(updateSql, new Object[]{frozeReason, startTime, endTime, frozeId});
                return ApiResponse.success(null,"修改封禁成功");
            } else {
                return ApiResponse.error(500, "查询到多条封禁信息");
            }
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 查询账号封禁
    @GetMapping("/getBanUser")
    public ApiResponse<Map<String, Object>> getBanUser(@RequestParam String frozeId) {
        if (frozeId == "")
            return ApiResponse.error(400, "请求参数错误");
        try {
            String sql="select * from account_froze where frozeId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, frozeId);
            if (results.isEmpty()) return ApiResponse.error( 404,"该账户无不良信息");
            if (results.size() > 1) return ApiResponse.error(500, "查询到多条封禁信息");
            Map<String, Object> data = results.get(0);
            return ApiResponse.success(data,"无不良行为");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 修改用户系统权限
    @RequestMapping("/updateIsManager")
    public ApiResponse<String> updateIsManager(@RequestBody UserInfo data){
        String openId = data.getOpenId();
        String identity = data.getIdentity();
        if (openId == "")
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql="update user_info set identity=? where openId=?";
            jdbc.update(sql, new Object[]{identity, openId});
            return ApiResponse.success(null,"Permission changed to "+identity);
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

//    // 根据openId删除user_info中的记录（注销）
//    @RequestMapping("/deleteUser")
//    public ApiResponse<String> deleteUser(){
//        String sql="delete from user_info where openId=123123";
//        jdbc.update(sql);
//        return ApiResponse.success("delete success");
//    }
//


}
