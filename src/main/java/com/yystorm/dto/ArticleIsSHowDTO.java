package com.yystorm.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ArticleIsSHowDTO {
    @NotBlank
    private String id;
    @NotBlank(message = "文章标题不能为空")
    private String title;
    @NotNull(message = "用户不能为空")
    private Integer userId;


}
