package com.yystorm.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticleCategoryVo implements Serializable {
    private Integer id;
    private String name;
    private Integer sort;
    private List<ArticleCategoryTwoVo> categoryList;

}
