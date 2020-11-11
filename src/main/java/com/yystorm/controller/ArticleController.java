package com.yystorm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.email.IMailService;
import com.yystorm.entity.Article;
import com.yystorm.execptionhandler.GuliException;
import com.yystorm.handler.JwtToken;
import com.yystorm.mapper.ArticleMapper;
import com.yystorm.service.ArticleService;
import com.yystorm.utils.Result;
import com.yystorm.vo.ArticleVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;



    /**
     * 根据分类id 查询文章
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category")
    public Result getCategoryList(@RequestParam("categoryId") String categoryId,
                                  @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                  @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        if (StringUtils.isEmpty(categoryId)) return Result.error().message("参数不能为空");
        Page<Article> page1 = new Page<>(page, size);
        IPage<ArticleVO> e = articleService.getCategoryListMapper(page1, categoryId);

        return Result.ok().data("data", e);
    }
    @GetMapping("/search")
    public Result getSearchList(@RequestParam("name") String name,
                                  @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                  @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        if (StringUtils.isEmpty(name)) return Result.error().message("参数不能为空");
        Page<Article> page1 = new Page<>(page, size);
        IPage<ArticleVO> listMapper = articleService.getSearchList(page1, name);

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
     * 热门排行
     *
     * @return
     */
    @GetMapping("/hot")
    public Result hotArticle() {
        List<ArticleVO> list = articleService.HotArticle();
        return Result.ok().data("data", list);
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

    @GetMapping("/random")
    public Result randomArticleList(@RequestParam(value = "id",required = true) String id){
        List<Article> articleList = articleService.randomArticle(id);
        return Result.ok().data("data",articleList);
    }
}

