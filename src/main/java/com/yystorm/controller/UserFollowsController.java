package com.yystorm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.dto.UserFollowsDTO;
import com.yystorm.entity.Article;
import com.yystorm.entity.User;
import com.yystorm.entity.UserFollows;
import com.yystorm.handler.JwtToken;
import com.yystorm.mapper.UserFollowsMapper;
import com.yystorm.service.UserFollowsService;
import com.yystorm.utils.Result;
import com.yystorm.vo.UserFollowsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-24
 */
@RestController
@RequestMapping("/userfollows")
public class UserFollowsController {
    @Autowired
    private UserFollowsService userFollowsService;


    /**
     * 关注
     *
     * @param userFollowsDTO
     * @return
     */
    @PostMapping
    @JwtToken
    public Result addUserFollows(@Validated @RequestBody UserFollowsDTO userFollowsDTO) {
        if (userFollowsDTO.getUserId().equals(userFollowsDTO.getFollowedUser()))
            return Result.error().message("不能自己关注自己");
        int userFollows = userFollowsService.isUserFollows(userFollowsDTO);
        if (userFollows > 0) {
            return Result.error().message("已经关注");
        }
        int i = userFollowsService.addUserFollows(userFollowsDTO);
        if (i > 0) {
            return Result.ok().message("关注成功");
        }
        return Result.error().message("关注失败");
    }

    /**
     * 查看当前用户所有的关注
     *
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/all/{id}")
    public Result getUserFollowsList(@PathVariable("id") String id,
                                     @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                     @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Page<UserFollowsVO> page1 = new Page<>(page, size);
        IPage<UserFollowsVO> e = userFollowsService.slectPageMap(page1, id);
        return Result.ok().data("list", e);
    }

    /**
     * 取消关注
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delUserFollowsList(@PathVariable("id") String id) {
        int i = userFollowsService.delUserFollows(id);
        if (i > 0) {
            return Result.ok().message("取消成功");
        }
        return Result.error().message("取消失败");
    }

    /**
     * 查看粉丝
     *
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/fan/{id}")
    public Result getUserFanList(@PathVariable("id") String id,
                                 @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                 @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Page<UserFollowsVO> page1 = new Page<>(page, size);
        IPage<UserFollowsVO> e = userFollowsService.slectPageFanMap(page1, id);
        return Result.ok().data("list", e);
    }

    @PostMapping("/verification")
    @JwtToken
    public Result isUserFollows(@Validated @RequestBody UserFollowsDTO userFollowsDTO) {
        return Result.ok().data("list", userFollowsService.isUserFollows(userFollowsDTO));

    }

}

