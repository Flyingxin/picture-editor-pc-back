package com.controller;

import com.common.ApiResponse;
import com.entity.user.AccountVipInfo;
import com.entity.user.UserInfo;
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
 * date 2024/3/10
 * description 接口测试完毕、
 * defect 未接入日志系统
 * */

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
    @Autowired
    JdbcTemplate jdbc;

    // 查询VIP
    @RequestMapping("/getUserVip")
    public ApiResponse<Map<String, Object>> getUserVip(@RequestParam String userVipId) {
        if (userVipId.isEmpty())
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql = "select * from account_vip_info where userVipId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, userVipId);
            if (results.isEmpty()) return ApiResponse.error(204, "暂无VIP");
            if (results.size() > 1) return ApiResponse.error(500, "查询到多条VIP信息");
            Map<String, Object> data = results.get(0);

            return ApiResponse.success(data, "查询成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 续费VIP
    @RequestMapping("/chargeUserVip")
    public ApiResponse<String> chargeUserVip(@RequestBody AccountVipInfo data) {
//        String openId = data.getOpenId();
        String userVipId = data.getUserVipId();
        String endTime = data.getEndTime();
        String gradeScore = data.getGradeScore();
        if (userVipId.isEmpty() || endTime.isEmpty() || gradeScore.isEmpty())
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql = "select * from account_vip_info where userVipId = ?";
            List<Map<String, Object>> results = jdbc.query(sql, new Object[]{userVipId}, new ColumnMapRowMapper());
            if (results.isEmpty()) return ApiResponse.error(404, "账号不存在，请先注册");
            if (results.size() == 1) {
                Map<String, Object> vipInfo = results.get(0);
                int originScore = (int) vipInfo.get("gradeScore");
                int currentScore = Integer.parseInt(gradeScore) + originScore;
                sql = "update account_vip_info set endTime=?,gradeScore=? where userVipId=?";
                jdbc.update(sql, new Object[]{endTime, currentScore, userVipId});
            } else {
                return ApiResponse.error(404, "查询到多条VIP信息");
            }
            return ApiResponse.success(null, "续费成功");
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 关闭VIP
    @RequestMapping("/closeVip")
    public ApiResponse<String> closeVip(@RequestParam String userVipId) {
        if (userVipId.isEmpty())
            return ApiResponse.error(400, "请求参数错误");

        try {
            String sql = "delete from account_vip_info where userVipId=?";
            jdbc.update(sql, new Object[]{userVipId});
            return ApiResponse.success(null, "关闭改用户VIP成功");
        } catch (Exception e) {
            return ApiResponse.error(500, "服务器崩溃");
        }
    }

    // 修改用户权限
    @RequestMapping("/updateUserPrivilege")
    public ApiResponse<String> updateUserPrivilege(@RequestBody UserInfo data) {
        String openId = data.getOpenId();
        String identity = data.getIdentity();
        if (openId.isEmpty() || identity.isEmpty())
            return ApiResponse.error(400, "请先查询用户信息");
        try {
            String sql = "update user_info set identity=? where openId=?";
            jdbc.update(sql, new Object[]{identity, openId});
            return ApiResponse.success(null, "成功修改为" + identity);
        } catch (DataAccessException e) {
            return ApiResponse.error(500, "服务器崩溃" + e.getMessage());
        }
    }

}
