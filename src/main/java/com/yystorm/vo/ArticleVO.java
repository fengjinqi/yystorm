package com.yystorm.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yystorm.entity.ArticleCategory;
import com.yystorm.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ArticleVO implements Serializable {
    @ApiModelProperty(value = "id")

    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面图")
    private String listPic;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "阅读")
    private Integer clickNums;

    @ApiModelProperty(value = "是否审核")
    private Boolean isShow;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Boolean isDelete;


    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "用户")
    private User user;

    @ApiModelProperty(value = "粉丝")
    private Integer fansCount;

    @ApiModelProperty(value = "关注")
    private Integer followCount;

    @ApiModelProperty(value = "分类")
    private ArticleCategory category;

    @ApiModelProperty(value = "评论数量")
    private Integer articleCommentCount;

}

