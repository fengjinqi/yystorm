package com.yystorm.controller;


import com.yystorm.dto.ArticleCommentDTO;
import com.yystorm.entity.ArticleComment;
import com.yystorm.handler.JwtToken;
import com.yystorm.service.ArticleCommentService;
import com.yystorm.utils.Result;
import com.yystorm.vo.ArticleCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/articlecomment")
public class ArticleCommentController {
    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * 发布评论
     *
     * @param articleCommentDTO
     * @return
     */
    @PostMapping
    @JwtToken
    public Result addArticleComment(@RequestBody @Validated ArticleCommentDTO articleCommentDTO) {
        int i = articleCommentService.addArticleComment(articleCommentDTO);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("/{id}")
    public Result getArticleComment(@PathVariable("id") String id) {
        List<ArticleCommentVO> articleCommentVOS = articleCommentService.getArticleCommentId(id);

        return Result.ok().data("list", articleCommentVOS);
    }

}

