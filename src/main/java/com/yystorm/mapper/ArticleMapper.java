package com.yystorm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystorm.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-16
 */

public interface ArticleMapper extends BaseMapper<Article> {

    //自定义分页查询，方式二
    //IPage<Map<String, Object>> getCategoryListMapper(Page<Article> page, @Param("id") String categoryId);
    //返回对象类型
    IPage<ArticleVO> getCategoryListMapper(Page<Article> page, @Param("id") String categoryId);

    ArticleVO selectOneById( @Param("id") String id);

    @Update("UPDATE article SET click_nums=click_nums+1 WHERE is_delete=0 and id=#{id}")
    void updateDetaileClickNums(String id);

    @Select("SELECT article.id,article.list_pic,article.title,article.gmt_modified FROM  article WHERE article.category_id=#{id} ORDER BY RAND() LIMIT 6")
    List<Article> randomArticle(String id);

    IPage<ArticleVO> getSearchList(Page<Article> page1, String name);

    /**
     * 文章列表
     * @param page1
     * @param name
     * @param categoryId
     * @return
     */
    IPage<ArticleVO> getAdminListMapper(Page<Article> page1,@Param("name") String name, @Param("id") String categoryId);


}
