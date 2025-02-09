package com.controller;

import com.response.BaseResponse;
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
    public BaseResponse<Map<String, Object>> getUserVip(@RequestParam String userVipId) {
        if (userVipId.isEmpty())
            return BaseResponse.fail(400, "请求参数错误");

        try {
            String sql = "select * from account_vip_info where userVipId = ?";
            List<Map<String, Object>> results = jdbc.queryForList(sql, userVipId);
            if (results.isEmpty()) return BaseResponse.fail(204, "暂无VIP");
            if (results.size() > 1) return BaseResponse.fail(500, "查询到多条VIP信息");
            Map<String, Object> data = results.get(0);

            return BaseResponse.success(data, "查询成功");
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 续费VIP
    @RequestMapping("/chargeUserVip")
    public BaseResponse<String> chargeUserVip(@RequestBody AccountVipInfo data) {
//        String openId = data.getOpenId();
        String userVipId = data.getUserVipId();
        String endTime = data.getEndTime();
        String gradeScore = data.getGradeScore();
        if (userVipId.isEmpty() || endTime.isEmpty() || gradeScore.isEmpty())
            return BaseResponse.fail(400, "请求参数错误");

        try {
            String sql = "select * from account_vip_info where userVipId = ?";
            List<Map<String, Object>> results = jdbc.query(sql, new Object[]{userVipId}, new ColumnMapRowMapper());
            if (results.isEmpty()) return BaseResponse.fail(404, "账号不存在，请先注册");
            if (results.size() == 1) {
                Map<String, Object> vipInfo = results.get(0);
                int originScore = (int) vipInfo.get("gradeScore");
                int currentScore = Integer.parseInt(gradeScore) + originScore;
                sql = "update account_vip_info set endTime=?,gradeScore=? where userVipId=?";
                jdbc.update(sql, new Object[]{endTime, currentScore, userVipId});
            } else {
                return BaseResponse.fail(404, "查询到多条VIP信息");
            }
            return BaseResponse.success(null, "续费成功");
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }

    // 关闭VIP
    @RequestMapping("/closeVip")
    public BaseResponse<String> closeVip(@RequestParam String userVipId) {
        if (userVipId.isEmpty())
            return BaseResponse.fail(400, "请求参数错误");

        try {
            String sql = "delete from account_vip_info where userVipId=?";
            jdbc.update(sql, new Object[]{userVipId});
            return BaseResponse.success(null, "关闭改用户VIP成功");
        } catch (Exception e) {
            return BaseResponse.fail(500, "服务器崩溃");
        }
    }

    // 修改用户权限
    @RequestMapping("/updateUserPrivilege")
    public BaseResponse<String> updateUserPrivilege(@RequestBody UserInfo data) {
        String openId = data.getOpenId();
        String identity = data.getIdentity();
        if (openId.isEmpty() || identity.isEmpty())
            return BaseResponse.fail(400, "请先查询用户信息");
        try {
            String sql = "update user_info set identity=? where openId=?";
            jdbc.update(sql, new Object[]{identity, openId});
            return BaseResponse.success(null, "成功修改为" + identity);
        } catch (DataAccessException e) {
            return BaseResponse.fail(500, "服务器崩溃" + e.getMessage());
        }
    }

}
