package com.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account_froze")
public class UserPicture implements Serializable {
    @Id
    private String id;
    private String userPictureId;
    private String pictureId;
    // base64
    private String pictureUrl;
    private String createTime;
    // 更新时间
    private String updateTime;
    // 图片类型
    private String pictureType;
    private String  pictureSuffix;

}
