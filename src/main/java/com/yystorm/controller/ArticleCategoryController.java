package com.yystorm.controller;


import com.yystorm.entity.ArticleCategory;
import com.yystorm.service.ArticleCategoryService;
import com.yystorm.utils.Result;
import com.yystorm.vo.ArticleCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/article-category")
public class ArticleCategoryController {
    @Autowired
    private ArticleCategoryService articleCategoryService;

    /**
     * 获取所有分类菜单
     * @return
     */
    @GetMapping
    public Result fintParent(){
        List<ArticleCategoryVo> parentArticleCategory = articleCategoryService.findParentArticleCategory();
        return Result.ok().data("list",parentArticleCategory);
    }

}

