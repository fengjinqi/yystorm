package com.yystorm.vo;

import com.yystorm.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserFollowsVO implements Serializable {
    private Integer id;

    private Integer articleCount;
    //粉丝
    private User fans;
    //关注
    private User follow;
    private Integer followCount;

    private Integer isOther;
}
