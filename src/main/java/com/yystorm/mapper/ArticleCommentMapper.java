package com.yystorm.mapper;

import com.yystorm.entity.ArticleComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystorm.vo.ArticleCommentVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-23
 */
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    List<ArticleCommentVO> selectListArticleComment(String id);
}
