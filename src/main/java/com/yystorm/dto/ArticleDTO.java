package com.yystorm.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ArticleDTO {
    private String id;
    @NotBlank(message = "文章标题不能为空")
    private String title;
    @NotEmpty(message = "文章封面图不能为空")
    private String listPic;
    @NotEmpty(message = "文章内容不能为空")
    private String content;
    @NotNull(message = "用户不能为空")
    private Integer userId;
    @NotNull(message = "文章分类不能为空")
    private Integer categoryId;

}
