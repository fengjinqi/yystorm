package com.yystorm.service;

import com.yystorm.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yystorm.vo.ArticleCategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-15
 */

public interface ArticleCategoryService extends IService<ArticleCategory> {
    List<ArticleCategoryVo> findParentArticleCategory();

}
