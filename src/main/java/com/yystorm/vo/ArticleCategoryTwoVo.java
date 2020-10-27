package com.yystorm.vo;

import com.yystorm.entity.ArticleCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticleCategoryTwoVo implements Serializable {
    private Integer id;
    private String name;
    private Integer sort;


}
