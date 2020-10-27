package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yystorm.dto.ArticleCommentDTO;
import com.yystorm.entity.Article;
import com.yystorm.entity.ArticleComment;
import com.yystorm.mapper.ArticleCommentMapper;
import com.yystorm.service.ArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.vo.ArticleCommentVO;
import com.yystorm.vo.ArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-23
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {
    /**
     * 创建评论
     * @param articleCommentDTO
     * @return
     */
    @Override
    public int addArticleComment(ArticleCommentDTO articleCommentDTO) {
        ArticleComment articleComment = new ArticleComment();
        BeanUtils.copyProperties(articleCommentDTO,articleComment);
        return baseMapper.insert(articleComment);
    }

    @Override
    public List<ArticleCommentVO> getArticleCommentId(String id) {
       List<ArticleCommentVO> articleCommentVOList= baseMapper.selectListArticleComment(id);
        return articleCommentVOList;
    }
}
