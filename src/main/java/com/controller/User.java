package com.controller;

import com.common.ApiResponse;
import com.model.dto.user.UserInfoDTO;
import com.model.dto.user.VipChargeRecordDTO;
import com.utils.GenerateString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.jwt.JwtUtil.generateToken;
import static com.utils.GenerateString.generateUUID;
import static com.utils.GenerateString.getCurrentDateTime;

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

            // 用户封禁（results中的frozeId字段用来查询account_ban表）
            String frozeId = data.get("frozeId").toString();
            sql="select * from account_froze where frozeId = ?";
            List<Map<String, Object>> frozeResults = jdbc.query(sql, new Object[]{frozeId}, new ColumnMapRowMapper());
            // 比较是否在超过解冻时间(还有错)
            if (!frozeResults.isEmpty()) {
//                String endTimeStr = frozeResults.get(0).get("endTime").toString(); // 2024-04-27T00:00 应该是这里的错
//                System.out.println(frozeResults);
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date endTime = formatter.parse(endTimeStr);
//                Date currentTime = new Date();
//                if (endTime.before(currentTime)) {
                    return ApiResponse.success(frozeResults.get(0), "账号已被封禁");
//                }
            }

            // 修改用户登录时间
            String openId = data.get("openId").toString();
            String lastLoginTime = getCurrentDateTime();
            sql="update user_info set lastLoginTime = ? where openId = ?";
            jdbc.update(sql, new Object[]{lastLoginTime, openId});

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
            String sql = "select count(*) from user_info where telephone = ?";
            int count = jdbc.queryForObject(sql, new Object[]{telephone}, Integer.class);
            if (count > 0) return ApiResponse.error(409, "用户已存在");

            // 用户功能映射表
            String userVipId = generateUUID(20);
            String userPictureId = generateUUID(20);
            String userApiId = generateUUID(20);
            sql = "insert into user_feature_associations(openId,userVipId,userPictureId,userApiId) values(?,?,?,?)";
            jdbc.update(sql,openId,userVipId,userPictureId,userApiId);

            // 默认头像地址
            String avatar = "http://localhost:9000/api/icon/defaultAvatar.png";
            String frozeId = generateUUID(20);
            String createTime = getCurrentDateTime();
            sql="insert into user_info(openId,avatar,telephone,password,nickName,identity,createTime,frozeId) values(?,?,?,?,?,?,?,?)";
            jdbc.update(sql,openId,avatar,telephone,password,nickName,"user",createTime,frozeId);
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


    // 根据openId查询user_feature_associations获取功能表数据
    @RequestMapping("/getUserFeatureAssociations")
    public ApiResponse<Map<String, Object>> getUserFeatureAssociations(@RequestParam String openId){
        if (openId == "")
            return ApiResponse.error(400, "openId不能为空");
        try {
            String sql="select * from user_feature_associations where openId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, openId);
            if (results.isEmpty()) return ApiResponse.error(404, "未查询到用户权限信息，请联系管理员");
            if (results.size() > 1) return ApiResponse.error(500, "查询到多条用户权限信息，请联系管理员");
            Map<String, Object> data = results.get(0);
            return ApiResponse.success(data,"查询成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 开通VIP,根据userId查询account_vip_info表如果已经是VIP则续费，但都要给vip_charge_record表添加一条记录
    @RequestMapping("/chargeVip")
    public ApiResponse<String> chargeVip(@RequestBody VipChargeRecordDTO data) {
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
        if (userVipId == "" || chargeId == "" || payTime == "" || endTime == "" || price == null || unit == "" || payType == "" || operatingUser == "")
            return ApiResponse.error(400, "请求参数错误");

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
                return ApiResponse.error(500, "系统崩溃");
            }

            // 添加一条VIP充值记录
            sql = "insert into vip_charge_record(chargeId,userVipId,payTime,price,unit,score,payType,renewDays,operatingUser) values(?,?,?,?,?,?,?,?,?)";
            jdbc.update(sql, new Object[]{chargeId, userVipId, payTime, price, unit, score, payType, renewDays, operatingUser});
            return ApiResponse.success(null, "升级成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }
}
