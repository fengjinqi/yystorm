package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yystorm.entity.ArticleCategory;
import com.yystorm.mapper.ArticleCategoryMapper;
import com.yystorm.service.ArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.vo.ArticleCategoryTwoVo;
import com.yystorm.vo.ArticleCategoryVo;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-15
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {
    /**
     * 菜单分类
     * @return
     */
    @Override
    @Cacheable(value = "category",key = "'selectIndexList'")
    public List<ArticleCategoryVo> findParentArticleCategory() {
        List<ArticleCategory> parent = baseMapper.selectList(new QueryWrapper<ArticleCategory>().eq("parent", "0"));
        List<ArticleCategory> categoryList = baseMapper.selectList(new QueryWrapper<ArticleCategory>().ne("parent", "0"));
        List<ArticleCategoryVo> list = new ArrayList<>();
        for (ArticleCategory articleCategory : parent) {
            ArticleCategoryVo articleCategoryVo = new ArticleCategoryVo();
            BeanUtils.copyProperties(articleCategory, articleCategoryVo);
            list.add(articleCategoryVo);
            List<ArticleCategoryTwoVo> twoVos = new ArrayList<>();
            for (ArticleCategory category : categoryList) {
                if (category.getParent().equals(articleCategoryVo.getId())) {
                    ArticleCategoryTwoVo articleCategoryTwoVo = new ArticleCategoryTwoVo();
                    BeanUtils.copyProperties(category, articleCategoryTwoVo);
                    twoVos.add(articleCategoryTwoVo);
                }
            }
            articleCategoryVo.setCategoryList(twoVos);
        }
        return list;
    }
}
