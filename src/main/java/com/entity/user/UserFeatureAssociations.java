package com.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_feature_associations")
public class UserFeatureAssociations {
    @Id
    private long id;
    private String openId;
    private String userVipId;
    private String userPictureId;
    private String userApiId;
}
