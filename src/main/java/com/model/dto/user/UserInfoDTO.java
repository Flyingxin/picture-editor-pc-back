package com.model.dto.user;


// 用户信息表 user_info
public class UserInfoDTO {
    private String id;
    private String openId;
    private String avatar;
    private String telephone;
    private String nickName;
    private String password;
    private String name;
    private String sex;
    private String idCard;
    private String birthday;
    private String email;
    private String identity;
    private String createTime;
    private String lastLoginTime;

    public String getId() { return id; }
    public String getOpenId() { return openId; }
    public String getAvatar() { return avatar; }
    public String getTelephone() {
        return telephone;
    }
    public String getNickName() { return nickName; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getSex() { return sex; }
    public String getIdCard() { return idCard; }
    public String getBirthday() { return birthday; }
    public String getEmail() { return email; }
    public String getIdentity() { return identity; }
    public String getCreateTime() { return createTime; }
    public String getLastLoginTime() { return lastLoginTime; }
}