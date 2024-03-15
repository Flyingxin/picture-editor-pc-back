package com.controller;

import com.common.ApiResponse;
import com.model.dto.user.AccountBanDTO;
import com.model.dto.user.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accountManagement")
public class AccountManagement {
    @Autowired
    JdbcTemplate jdbc;

    // 修改账号封禁(可以继续扩展为，封禁后，用户无法登录，但是可以查看自己的信息，但是无法进行任何操作，包括修改信息，删除信息等等)
    // 且数据库还可以扩展为，字段isBan添加到user_info中用来登录判断（有则查account_ban符合的记录放回），account_ban表允许openId有多条记录，用来后台行为分析
    @RequestMapping("/banUser")
    public ApiResponse<List<Map<String, Object>>> BanUser(@RequestBody AccountBanDTO data){
        String openId = data.getOpenId();
        String banReason = data.getBanReason();
        String startTime = data.getStartTime();
        String endTime = data.getEndTime();
        if (openId == "" || banReason == "" || startTime == "" || endTime == "")
            return ApiResponse.error(400,"请求参数错误");

        try {
            String sql="select * from account_ban where openId = ?";
            List<Map<String, Object>> results = jdbc.query(sql, new Object[]{openId}, new ColumnMapRowMapper());
            if (results.size() == 0) {
                sql="insert into account_ban(openId,banReason,startTime,endTime) values(?,?,?,?)";
                jdbc.update(sql, new Object[]{openId, banReason, startTime, endTime});
                return ApiResponse.success(results,"封禁成功");
            } else if(results.size() == 1){
                String updateSql="update account_ban set banReason=?,startTime=?,endTime=? where openId=?";
                jdbc.update(updateSql, new Object[]{banReason, startTime, endTime, openId});
                return ApiResponse.success(results,"修改封禁成功");
            } else {
                return ApiResponse.error(500, "查询到多条封禁信息");
            }
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 查询账号封禁
    @GetMapping("/getBanUser")
    public ApiResponse<Map<String, Object>> getBanUser(@RequestParam String openId) {
        if (openId == "")
            return ApiResponse.error(400, "请求参数错误");
        try {
            String sql="select * from account_ban where openId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, openId);
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
    public ApiResponse<String> updateIsManager(@RequestBody UserInfoDTO data){
        String openId = data.getOpenId();
        String isManager = data.getIsManager();
        if (openId == "")
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql="update user_info set isManager=? where openId=?";
            jdbc.update(sql, new Object[]{isManager, openId});
            return ApiResponse.success(null,"Permission changed to "+isManager);
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 根据openId修改用户信息
    @RequestMapping("/updateUserInfo")
    public ApiResponse<String> updateUserInfo(@RequestBody UserInfoDTO data){
        String avatar = data.getAvatar();
        String telephone = data.getTelephone();
        String nickName = data.getNickName();
        String password = data.getPassword();
        String name = data.getName();
        String sex = data.getSex();
        String idCard = data.getIdCard();
        String birthday = data.getBirthday();
        String email = data.getEmail();
        if(avatar == "" || nickName == "" ||
            telephone == "" || idCard == "" || birthday == "" ||
            sex == "" || email == "" || password == "" || name == "")
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql="update user_info set avatar=?,telephone=?,nickName=?,password=?,name=?,sex=?,idCard=?,birthday=?,email=? where telephone=?";
            jdbc.update(sql, new Object[]{avatar, telephone, nickName, password, name,sex,idCard,birthday, email, telephone});
            return ApiResponse.success(null,"修改成功");
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
