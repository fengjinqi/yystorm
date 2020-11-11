package com.yystorm.service.impl;

import com.yystorm.dto.ArticleCommentDTO;
import com.yystorm.entity.ArticleComment;
import com.yystorm.entity.Messages;
import com.yystorm.mapper.ArticleCommentMapper;
import com.yystorm.service.ArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.service.MessagesService;
import com.yystorm.utils.MessageEnum;
import com.yystorm.vo.ArticleCommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-23
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Autowired
    private MessagesService messagesService;

    /**
     * 创建评论
     *
     * @param articleCommentDTO
     * @return
     */
    @Override
    @Transactional
    public int addArticleComment(ArticleCommentDTO articleCommentDTO) {
        ArticleComment articleComment = new ArticleComment();
        BeanUtils.copyProperties(articleCommentDTO, articleComment);
        Messages messages = new Messages();
        messages.setContent(String.format("你发布的%s文章被人评论了,快去看看吧！","<span>"+articleCommentDTO.getTitle()+"</span>"));
        messages.setTitle(MessageEnum.COMMENT);
        messages.setContentId(articleComment.getArticleId());
        messages.setAcceptId(articleCommentDTO.getAcceptId());
        messages.setCreateId(articleComment.getUserId());
        messagesService.createMessages(messages);
        return baseMapper.insert(articleComment);
    }

    @Override
    public List<ArticleCommentVO> getArticleCommentId(String id) {
        return baseMapper.selectListArticleComment(id);
    }

    /**
     * 我的评论
     * @param id
     * @return
     */
    @Override
    public List<ArticleCommentVO> getMeCommentId(String id) {
        return baseMapper.selectMeListArticleComment(id);
    }
}
