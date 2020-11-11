package com.yystorm.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class UserVo implements Serializable {
    @ApiModelProperty(value = "id")

    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;


    @ApiModelProperty(value = "用户头像")
    private String userImg;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;


    @ApiModelProperty(value = "支付宝")
    private String alipay;

    @ApiModelProperty(value = "信息")
    private String info;
    @ApiModelProperty(value = "粉丝")
    private Integer fansCount;

    @ApiModelProperty(value = "关注")
    private Integer followCount;

    @ApiModelProperty(value = "角色")
    private Integer role;
}
