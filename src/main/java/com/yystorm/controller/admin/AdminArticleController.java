package com.yystorm.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.dto.ArticleIsSHowDTO;
import com.yystorm.entity.Article;
import com.yystorm.handler.JwtToken;
import com.yystorm.mapper.ArticleMapper;
import com.yystorm.service.ArticleService;
import com.yystorm.utils.Result;
import com.yystorm.vo.ArticleVO;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;



    @JwtToken
    @GetMapping("/search")
    public Result getSearchList(@RequestParam(value = "name",required = false) String name,
                                @RequestParam(value = "categoryId",required = false) String categoryId,
                                  @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                  @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Page<Article> page1 = new Page<>(page, size);
        IPage<ArticleVO> listMapper = articleService.getAdminListMapper(page1, name,categoryId);

        return Result.ok().data("data", listMapper);
    }

    /**
     * 创建文章
     *
     * @param articleDTO
     * @return
     */
    @PostMapping
    @JwtToken
    public Result createArticle(@RequestBody @Validated ArticleDTO articleDTO) {
        Integer article = articleService.createArticle(articleDTO);
        if (article > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 修改文章
     * @param articleDTO
     * @return
     */
    @PutMapping
    @JwtToken
    public Result putArticle(@RequestBody @Validated ArticleDTO articleDTO) {
        Integer article = articleService.updateArticle(articleDTO);
        if (article > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 文章详情页
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getArticleFindId(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) return Result.error().message("id is not");
        ArticleVO articleVO = articleService.getArticleDetaile(id);
        List<Article> articles = articleService.randomArticle(articleVO.getCategory().getId().toString());
        return Result.ok().data("data", articleVO).data("random",articles);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @JwtToken
    public Result delArticle(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) return Result.error().message("id is not");
        int id1 = articleMapper.delete(new QueryWrapper<Article>().eq("id", id));
        if (id1 > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 审核
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @JwtToken
    public Result putArticle(@PathVariable("id") String id,@Validated @RequestBody ArticleIsSHowDTO articleIsSHowDTO) {
        if (StringUtils.isEmpty(id)) return Result.error().message("id is not");

        int id1 = articleService.updateByIdIsShow(articleIsSHowDTO);
        if (id1 > 0) {
            return Result.ok();
        }
        return Result.error();
    }

}

