package com.service.impl;

import com.common.ApiResponse;
import com.dao.user.AccountFrozeDao;
import com.dao.user.UserFeatureAssociationsDao;
import com.dao.user.UserInfoDao;
import com.entity.user.UserInfo;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utils.Date;

import java.time.LocalDateTime;

import com.utils.GenerateString;

import static com.utils.GenerateString.generateUUID;
import static com.utils.GenerateString.getCurrentDateTime;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private AccountFrozeDao accountFrozeDao;

    @Autowired
    private UserFeatureAssociationsDao userFeatureAssociationsDao;

    @Override
    public ApiResponse<UserInfo> login(String telephone, String password) {

        UserInfo userInfo = userInfoDao.queryUser(telephone);
        if (userInfo.getId().isEmpty()) {
            return ApiResponse.error(204, "账号不存在，请先注册");
        }

        if (!password.equals(userInfo.getPassword()))  {
            return ApiResponse.error(204, "账号或密码错误");
        }

//        AccountFroze frozeInfo = accountFrozeDao.queryUserForFroze(userInfo.getFrozeId());

        // 当前时间与冻结时间
        LocalDateTime currentDateTime = Date.stringToLocalDateTime(Date.getCurrentDateTime());
//        LocalDateTime frozeEndTime = Date.stringToLocalDateTime(frozeInfo.getEndTime());
//        if (frozeEndTime.isBefore(currentDateTime)) {
//            return ApiResponse.error(204, "账号已被封禁");
//        }

        // 修改最新登录时间
        userInfoDao.freshLoginTime(userInfo.getOpenId(), currentDateTime);

        return ApiResponse.success(userInfo, "成功");
    }

    @Override
    public ApiResponse<String> register(UserInfo userInfo) {
        String openId = userInfo.getOpenId();
        String telephone = userInfo.getTelephone();
        String password = userInfo.getPassword();
        String nickName = userInfo.getNickName();
        if (openId.isEmpty() || telephone.isEmpty() || password.isEmpty() || nickName.isEmpty()) {
            return ApiResponse.error(400, "请填写完整的信息");
        }

        UserInfo data = userInfoDao.queryUser(telephone);
        if (data != null) {
            return ApiResponse.error(204, "账号已存在");
        }

        // 添加用户功能映射id
        String userVipId = GenerateString.generateUUID(20);
        String userPictureId = GenerateString.generateUUID(20);
        String userApiId = GenerateString.generateUUID(20);
        userFeatureAssociationsDao.addFunctionId(openId, userVipId, userPictureId, userApiId);

        // 默认头像地址
        String avatar = "http://localhost:9000/api/icon/defaultAvatar.png";
        String frozeId = generateUUID(20);
        userInfo.setCreateTime(getCurrentDateTime());
        userInfo.setFrozeId(frozeId);
        userInfo.setAvatar(avatar);
        int id = userInfoDao.addUser(userInfo);

        return ApiResponse.success(id + "","注册成功");
    }
}
