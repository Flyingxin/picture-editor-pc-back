package com.controller;

import com.common.ApiResponse;
import com.model.dto.user.AccountVipInfoDTO;
import com.model.dto.user.UserInfoDTO;
import com.utils.GenerateString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户特权
 * 1. 开通VIP
 * 2. 查询VIP
 * 3. 续费VIP（未开发）
 * 4. 关闭VIP（未开发）
 * @author ccyx
 * @date 2024/3/10
 * @description 接口测试完毕、
 * @defect 未接入日志系统
 * */

@RestController
@RequestMapping("/privilege")
public class Privilege {
    @Autowired
    JdbcTemplate jdbc;

    // 开通VIP
    @RequestMapping("/openVip")
    public ApiResponse<String> openVip(@RequestBody AccountVipInfoDTO data) {
        String openId = data.getOpenId();
        String endTime = data.getEndTime();
        String gradeScore = data.getGradeScore();
        if (openId == "" || endTime == "" || gradeScore == "")
            return ApiResponse.error(400, "请求参数错误");

        // 根据openId查询是否已经开通VIP. 如果已经开通VIP则返回错误信息
        try {
            String selectSql = "select count(*) from account_vip_info where openId = ?";
            int count = jdbc.queryForObject(selectSql, new Object[]{openId}, Integer.class);
            if (count == 0) {
                try {
                    String createTime = GenerateString.getCurrentDateTime();
                    String sql = "insert into account_vip_info(openId,createTime,endTime,gradeScore) values(?,?,?,?)";
                    jdbc.update(sql, new Object[]{openId, createTime, endTime, gradeScore});
                    return ApiResponse.success(null,"开通VIP成功");
                } catch (Exception e) {
                    return ApiResponse.error(500, "服务器insert崩溃");
                }
            } else {
                return ApiResponse.error(200, "该用户已经开通VIP");
            }
        } catch (Exception e) {
            return ApiResponse.error(500, "服务器select崩溃");
        }
    }

    // 查询VIP
    @RequestMapping("/getUserVip")
    public ApiResponse<Map<String, Object>> getUserVip(@RequestParam String openId) {
        if (openId == "")
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql = "select * from account_vip_info where openId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, new Object[]{openId});
            if (results.isEmpty()) return ApiResponse.error(404, "未查询到VIP信息");
            if (results.size() > 1) return ApiResponse.error(500, "查询到多条VIP信息");
            Map<String, Object> data = results.get(0);

            return ApiResponse.success(data,"查询成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃"+e.getMessage());
        }
    }

    // 续费VIP
    @RequestMapping("/chargeUserVip")
    public ApiResponse<String> chargeUserVip(@RequestBody AccountVipInfoDTO data) {
        String openId = data.getOpenId();
        String endTime = data.getEndTime();
        String gradeScore = data.getGradeScore();
        if (openId == "" || endTime == "" || gradeScore == "")
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql = "select * from account_vip_info where openId = ?";
            List<Map<String, Object>> results = jdbc.query(sql, new Object[]{openId}, new ColumnMapRowMapper());
            if (results.isEmpty())  return ApiResponse.error(404, "账号不存在，请先注册");
            if (results.size() == 1) {
                Map<String, Object> vipInfo = results.get(0);
                int originScore = (int) vipInfo.get("gradeScore");
                int currentScore = Integer.parseInt(gradeScore) + originScore;
                sql = "update account_vip_info set endTime=?,gradeScore=? where openId=?";
                jdbc.update(sql, new Object[]{endTime, currentScore, openId});
            } else {
                return ApiResponse.error(404, "查询到多条VIP信息");
            }
            return ApiResponse.success(null,"续费成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃"+e.getMessage());
        }
    }

    // 关闭VIP
    @RequestMapping("/closeVip")
    public ApiResponse<String> closeVip(@RequestParam String openId) {
        if (openId == "")
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql = "delete from account_vip_info where openId=?";
            jdbc.update(sql, new Object[]{openId});
            return ApiResponse.success(null,"关闭改用户VIP成功");
        } catch (Exception e) {
            return ApiResponse.error(500, "服务器崩溃");
        }
    }

    // 修改用户权限
    @RequestMapping("/updateUserPrivilege")
    public ApiResponse<String> updateUserPrivilege(@RequestBody UserInfoDTO data){
        String openId = data.getOpenId();
        String isManager = data.getIsManager();
        if (openId.isEmpty() || isManager.isEmpty())
            return ApiResponse.error(400, "请先查询用户信息");
        try {
            String sql="update user_info set isManager=? where openId=?";
            jdbc.update(sql, new Object[]{isManager,openId});
            return ApiResponse.success(null,"修改成功" + isManager);
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

}
