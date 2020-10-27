package com.yystorm.service;

import com.yystorm.dto.ArticleCommentDTO;
import com.yystorm.entity.ArticleComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yystorm.vo.ArticleCommentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-23
 */
public interface ArticleCommentService extends IService<ArticleComment> {
    /**
     * 创建评论
     * @param articleCommentDTO
     * @return
     */
    int addArticleComment(ArticleCommentDTO articleCommentDTO);

    /**
     * 根据文章查询评论
     * @param id
     * @return
     */
    List<ArticleCommentVO> getArticleCommentId(String id);
}
