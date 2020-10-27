package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.entity.Article;
import com.yystorm.mapper.ArticleMapper;
import com.yystorm.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.vo.ArticleVO;
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
 * @since 2020-10-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public Integer createArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        return baseMapper.insert(article);
    }

    @Override
    public List<Article> findAllArticle() {
        return baseMapper.findAll();
    }

    /**
     * 分类查询文章
     *
     * @param page
     * @param categoryId
     * @return
     */
    @Override
    public IPage<ArticleDTO> getCategoryListMapper(Page<Article> page, String categoryId) {
        return baseMapper.getCategoryListMapper(page, categoryId);
    }

    /**
     * 热门排行
     *
     * @return
     */
    @Override
    public List<ArticleVO> HotArticle() {
        List<Article> list = baseMapper.selectList(new QueryWrapper<Article>()
                .eq("is_show", "1").orderByDesc("click_nums").last("limit 10"));
        List<ArticleVO> articleDTOList = new ArrayList<>();
        for (Article article : list) {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article, articleVO);
            articleDTOList.add(articleVO);
        }
        return articleDTOList;
    }


    /**
     * 文章详情页
     *
     * @param id
     * @return
     */
    @Override
    public ArticleVO getArticleDetaile(String id) {
        baseMapper.updateDetaileClickNums(id);
        return baseMapper.selectOneById(id);
    }

    /**
     * 修改文章
     *
     * @param articleDTO
     * @return
     */
    @Override
    public Integer updateArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        return baseMapper.updateById(article);
    }

    @Override
    public List<Article> randomArticle(String id) {
        return baseMapper.randomArticle(id);
    }


}
