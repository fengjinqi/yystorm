package com.yystorm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.dto.UserDTO;
import com.yystorm.entity.Article;
import com.yystorm.entity.User;
import com.yystorm.handler.JwtToken;
import com.yystorm.service.ArticleCommentService;
import com.yystorm.service.UserService;
import com.yystorm.utils.JwtUtils;
import com.yystorm.utils.Result;
import com.yystorm.vo.ArticleCommentVO;
import com.yystorm.vo.ArticleVO;
import com.yystorm.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * 获取当前用户信息
     *
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/info")
    @JwtToken
    public Result getUserInfo(HttpServletRequest httpServletRequest) {
        UserVo user = getUser(httpServletRequest);
        if (user == null) return Result.error().message("不存在token");


        return Result.ok().data("data", user);
    }

    /**
     * 查看其它用户
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result getUserInfoId(@PathVariable("id") String id) {
        UserVo userInfos = userService.selectOneId(id);

        return Result.ok().data("data", userInfos);
    }

    /**
     * 修改资料
     *
     * @param id
     * @param httpServletRequest
     * @return
     */
    @PutMapping("/{id}")
    @JwtToken
    public Result putUser(@RequestBody UserDTO userDTO, @PathVariable("id") String id, HttpServletRequest httpServletRequest) {
        boolean isValiedUser = isUser(id, httpServletRequest);
        if (isValiedUser) {
            int user = userService.putUser(userDTO, id);
            if (user > 0) {
                return Result.ok();
            } else {
                return Result.error().message("修改失败");
            }
        }
        return Result.error().message("当前登录用户不一致");

    }

    /**
     * 我的文章
     * @param page
     * @param size
     * @param httpServletRequest
     * @return
     */
    @JwtToken
    @GetMapping("/article")
    public Result findAllArticle(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                 @RequestParam(value = "size", defaultValue = "10", required = false) int size, HttpServletRequest httpServletRequest) {
        UserVo user = getUser(httpServletRequest);
        Page<Article> page1 = new Page<>(page, size);
        IPage<ArticleVO> result = userService.findAllArticle(page1, user.getId());
        return Result.ok().data("data", result);
    }

    /**
     * 其它用户的文章
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/article/{id}")
    public Result findAllArticle(@PathVariable("id")String id,@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                 @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Page<Article> page1 = new Page<>(page, size);
        IPage<ArticleVO> result = userService.findAllArticle(page1, Integer.valueOf(id));
        return Result.ok().data("data", result);
    }
    /**
     * 我的评论
     * @param httpServletRequest
     * @return
     */
    @JwtToken
    @GetMapping("/comment")
    public Result findAllArticle( HttpServletRequest httpServletRequest) {
        UserVo user = getUser(httpServletRequest);
        List<ArticleCommentVO> meCommentId = articleCommentService.getMeCommentId(user.getId().toString());
        return Result.ok().data("data", meCommentId);
    }

    /**
     * 验证是否当前用户
     *
     * @param id
     * @param httpServletRequest
     * @return
     */
    public boolean isUser(String id, HttpServletRequest httpServletRequest) {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        if (StringUtils.isEmpty(token)) return false;
        String usernameFromToken = jwtUtils.getUsernameFromToken(token);
        User userInfo = userService.getUserInfo(usernameFromToken);
        System.out.println(userInfo+"=====");
        if (id.equals(userInfo.getId().toString())) return true;
        return false;
    }

    /**
     * 获取当前user
     *
     * @param httpServletRequest
     * @return
     */
    public UserVo getUser(HttpServletRequest httpServletRequest) {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        if (StringUtils.isEmpty(token)) return null;
        String usernameFromToken = jwtUtils.getUsernameFromToken(token);
        return userService.getUserInfos(usernameFromToken);
    }


    @GetMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest httpServletRequest) {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        if (StringUtils.isEmpty(token)) return Result.error().data("msg", "丢失token");
        String usernameFromToken = jwtUtils.getUsernameFromToken(token);
        User userInfo = userService.getUserInfo(usernameFromToken);
        String token1 = jwtUtils.generateAccessToken(userInfo);
        return Result.ok().data("token", token1);
    }
}

