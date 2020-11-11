package com.yystorm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.entity.Article;
import com.yystorm.handler.JwtToken;
import com.yystorm.service.MessagesService;
import com.yystorm.utils.Result;
import com.yystorm.vo.MessagesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @GetMapping("/{id}")
    @JwtToken
    public Result getMessagezList(@PathVariable(value = "id", required = true) Integer id,
                                  @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                  @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Page<MessagesVO> page1 = new Page<>(page, size);
        IPage<MessagesVO>messagesList = messagesService.getMessagesList(page1,id);
        return Result.ok().data("data", messagesList);

    }
}

