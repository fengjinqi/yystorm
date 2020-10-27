package com.yystorm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UserFollowsDTO {
    @NotNull(message = "用户不能为空")
    private Integer userId;
    @NotNull(message = "用户不能为空")
    private Integer followedUser;
}
