package com.service.impl;

import com.dto.CommonAuthInfoDto;
import com.entity.user.AccountFroze;
import com.utils.jwt.JwtUtil;
import com.response.BaseResponse;
import com.mapper.user.AccountFrozeMapper;
import com.mapper.user.UserFeatureAssociationsMapper;
import com.mapper.user.UserInfoMapper;
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
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AccountFrozeMapper accountFrozeMapper;

    @Autowired
    private UserFeatureAssociationsMapper userFeatureAssociationsMapper;

    @Override
    public BaseResponse<UserInfo> login(String telephone, String password) {

        UserInfo userInfo = userInfoMapper.queryUser(telephone);
        if (userInfo.getId().isEmpty()) {
            return BaseResponse.fail(204, "账号不存在，请先注册");
        }

        if (!password.equals(userInfo.getPassword()))  {
            return BaseResponse.fail(204, "账号或密码错误");
        }

        AccountFroze frozeInfo = accountFrozeMapper.queryUserForFroze(userInfo.getFrozeId());

        // 当前时间与冻结时间
        LocalDateTime currentDateTime = Date.stringToLocalDateTime(Date.getCurrentDateTime());
        if(frozeInfo != null) {
            LocalDateTime frozeEndTime = Date.stringToLocalDateTime(frozeInfo.getEndTime());
            if (frozeEndTime.isBefore(currentDateTime)) {
                return BaseResponse.fail(204, "账号已被封禁");
            }
        }
        // token
        CommonAuthInfoDto authInfo = new CommonAuthInfoDto();
        authInfo.setApp("pictureEditor");
        authInfo.setUserId(userInfo.getOpenId());
        authInfo.setNick(userInfo.getNickName());
        authInfo.setRole("user");
        String token = JwtUtil.commonGenerateToken(authInfo);
        userInfo.setToken(token);

        // 修改最新登录时间
        userInfoMapper.freshLoginTime(userInfo.getOpenId(), currentDateTime);

        return BaseResponse.success(userInfo, "成功");
    }

    @Override
    public BaseResponse<String> register(UserInfo userInfo) {
        String openId = userInfo.getOpenId();
        String telephone = userInfo.getTelephone();
        String password = userInfo.getPassword();
        String nickName = userInfo.getNickName();
        if (openId.isEmpty() || telephone.isEmpty() || password.isEmpty() || nickName.isEmpty()) {
            return BaseResponse.fail(400, "请填写完整的信息");
        }

        UserInfo data = userInfoMapper.queryUser(telephone);
        if (data != null) {
            return BaseResponse.fail(204, "账号已存在");
        }

        // 添加用户功能映射id
        String userVipId = GenerateString.generateUUID(20);
        String userPictureId = GenerateString.generateUUID(20);
        String userApiId = GenerateString.generateUUID(20);
        userFeatureAssociationsMapper.addFunctionId(openId, userVipId, userPictureId, userApiId);

        // 默认头像地址
        String avatar = "http://localhost:9000/api/icon/defaultAvatar.png";
        String frozeId = generateUUID(20);
        userInfo.setCreateTime(getCurrentDateTime());
        userInfo.setFrozeId(frozeId);
        userInfo.setAvatar(avatar);
        int id = userInfoMapper.addUser(userInfo);

        return BaseResponse.success(id + "","注册成功");
    }
}
