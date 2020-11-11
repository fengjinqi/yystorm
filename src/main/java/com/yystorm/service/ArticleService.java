package com.yystorm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.dto.ArticleIsSHowDTO;
import com.yystorm.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yystorm.vo.ArticleVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-16
 */
public interface ArticleService extends IService<Article> {
    /**
     * 创建文章
     *
     * @param articleDTO
     * @return
     */
    Integer createArticle(ArticleDTO articleDTO);


    /**
     * 根据分类查询文章
     *
     * @param categoryId
     * @return
     */
    IPage<ArticleVO> getCategoryListMapper(Page<Article> page, String categoryId);

    /**
     * 热门文章
     *
     * @return
     */
    List<ArticleVO> HotArticle();

    /**
     * 文章详情页
     *
     * @param id
     * @return
     */
    ArticleVO getArticleDetaile(String id);

    /**
     * 修改文章
     *
     * @param articleDTO
     * @return
     */
    Integer updateArticle(ArticleDTO articleDTO);

    /**
     * 随机
     * @param id
     * @return
     */
    List<Article> randomArticle(String id);

    /**
     * 模糊搜索
     * @param page1
     * @param name
     * @return
     */
    IPage<ArticleVO> getSearchList(Page<Article> page1, String name);

    /**
     * 文章列表
     * @param page1
     * @param name
     * @param categoryId
     * @return
     */
    IPage<ArticleVO> getAdminListMapper(Page<Article> page1, String name, String categoryId);

    /**
     * 审核
     * @param id
     * @return
     */
    int updateByIdIsShow(ArticleIsSHowDTO id);
}
