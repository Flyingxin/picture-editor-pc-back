package com.controller;

import com.response.BaseResponse;
import com.entity.user.UserInfo;
import com.entity.user.VipChargeRecord;
import com.service.UserInfoService;
import com.utils.GenerateString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户信息
 * 1. 用户登录
 * 2. 用户注册
 * 3. 用户注销（未）
 * date 2024/3/8
 * @author ccyx
 * description 接口测试完毕、
 * defect 未接入日志系统
 * */

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    UserInfoService userInfoService;

    // 查询user-info账户密码登录
    @GetMapping("/login")
    public BaseResponse<UserInfo> login1(@RequestParam String telephone, @RequestParam String password){

        try {
            return userInfoService.login(telephone, password);
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃");
        }
    }

    // 注册用户
    @RequestMapping ("/register")
    public BaseResponse<String> register(@RequestBody UserInfo data){

        try {
            return userInfoService.register(data);
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "Database error: " + e.getMessage());
        }
    }

    // 根据电话号码查询用户信息
    @RequestMapping("/getUserInfoByTelephone")
    public BaseResponse<Map<String, Object>> getUserInfoByTelephone(@RequestParam String telephone){
        if (telephone.isEmpty())
            return BaseResponse.fail(400, "电话号码不能为空");
        try {
            String sql="select * from user_info where telephone = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, telephone);
            if (results.isEmpty()) return BaseResponse.fail(404, "未查询到用户信息");
            if (results.size() > 1) return BaseResponse.fail(500, "查询到多条用户信息");
            Map<String, Object> data = results.get(0);
            return BaseResponse.success(data,"查询成功");
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 根据openId查询用户信息
    @RequestMapping("/getUserInfoByOpenId")
    public BaseResponse<Map<String, Object>> getUserInfoByOpenId(@RequestParam String openId){
        if (openId.isEmpty())
            return BaseResponse.fail(400, "openId不能为空");
        try {
            String sql="select * from user_info where openId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, openId);
            if (results.isEmpty()) return BaseResponse.fail(404, "未查询到用户信息");
            if (results.size() > 1) return BaseResponse.fail(500, "查询到多条用户信息");
            Map<String, Object> data = results.get(0);
            return BaseResponse.success(data,"查询成功");
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 根据openId修改用户信息
    @RequestMapping("/updateUserInfo")
    public BaseResponse<String> updateUserInfo(@RequestBody UserInfo data){
        String avatar = data.getAvatar();
        String telephone = data.getTelephone();
        String nickName = data.getNickName();
        String password = data.getPassword();
        String name = data.getName();
        String sex = data.getSex();
        String idCard = data.getIdCard();
        String birthday = data.getBirthday();
        String email = data.getEmail();
        if(avatar.isEmpty() || nickName.isEmpty() ||
                telephone.isEmpty() || idCard.isEmpty() || birthday.isEmpty() ||
                sex.isEmpty() || email.isEmpty() || password.isEmpty() || name.isEmpty())
            return BaseResponse.fail(400, "请求参数错误");

        try {
            String sql="update user_info set avatar=?,telephone=?,nickName=?,password=?,name=?,sex=?,idCard=?,birthday=?,email=? where telephone=?";
            jdbc.update(sql, new Object[]{avatar, telephone, nickName, password, name,sex,idCard,birthday, email, telephone});
            return BaseResponse.success(null,"修改成功");
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }


    // 根据openId查询user_feature_associations获取功能表数据
    @RequestMapping("/getUserFeatureAssociations")
    public BaseResponse<Map<String, Object>> getUserFeatureAssociations(@RequestParam String openId){
        if (openId.isEmpty())
            return BaseResponse.fail(400, "openId不能为空");
        try {
            String sql="select * from user_feature_associations where openId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, openId);
            if (results.isEmpty()) return BaseResponse.fail(404, "未查询到用户权限信息，请联系管理员");
            if (results.size() > 1) return BaseResponse.fail(500, "查询到多条用户权限信息，请联系管理员");
            Map<String, Object> data = results.get(0);
            return BaseResponse.success(data,"查询成功");
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 开通VIP,根据userId查询account_vip_info表如果已经是VIP则续费，但都要给vip_charge_record表添加一条记录
    @RequestMapping("/chargeVip")
    public BaseResponse<String> chargeVip(@RequestBody VipChargeRecord data) {
        String userVipId = data.getUserVipId();
        String chargeId = data.getChargeId();
        String payTime = data.getPayTime();
        String endTime = data.getEndTime();
        Float price = data.getPrice();
        String unit = data.getUnit();
        int score = data.getScore();
        String payType = data.getPayType();
        int renewDays = data.getRenewDays();
        String operatingUser = data.getOperatingUser();
        if (userVipId.isEmpty() || chargeId.isEmpty() || payTime.isEmpty() || endTime.isEmpty() || price == null || unit.isEmpty() || payType.isEmpty() || operatingUser.isEmpty())
            return BaseResponse.fail(400, "请求参数错误");

        String createTime = GenerateString.getCurrentDateTime();
        try {
            String sql = "select * from account_vip_info where userVipId = ?";
            List<Map<String, Object>> results = jdbc.query(sql, new Object[]{userVipId}, new ColumnMapRowMapper());
            if (results.isEmpty()) {
                // 插入一条VIP信息
                String isForever = "0";
                sql = "insert into account_vip_info(userVipId,createTime,endTime,isForever,gradeScore) values(?,?,?,?,?)";
                jdbc.update(sql, userVipId, createTime, endTime, isForever, score);
            } else if (results.size() == 1){
                Map<String, Object> vipInfo = results.get(0);
                int originScore = (int) vipInfo.get("gradeScore");
                int currentScore = score + originScore;
                sql = "update account_vip_info set endTime=?,gradeScore=? where userVipId=?";
                jdbc.update(sql, new Object[]{endTime, currentScore, userVipId});
            }else {
                return BaseResponse.fail(500, "系统崩溃");
            }

            // 添加一条VIP充值记录
            sql = "insert into vip_charge_record(chargeId,userVipId,payTime,price,unit,score,payType,renewDays,operatingUser) values(?,?,?,?,?,?,?,?,?)";
            jdbc.update(sql, new Object[]{chargeId, userVipId, payTime, price, unit, score, payType, renewDays, operatingUser});
            return BaseResponse.success(null, "升级成功");
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }
}
