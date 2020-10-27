package com.yystorm.mapper;

import com.yystorm.entity.ArticleCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-15
 */
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {
    List findById();
}
