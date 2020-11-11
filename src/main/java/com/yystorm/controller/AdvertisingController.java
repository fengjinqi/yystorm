package com.yystorm.controller;


import com.yystorm.dto.AdvertisingDTO;
import com.yystorm.handler.JwtToken;
import com.yystorm.service.AdvertisingService;
import com.yystorm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/advertising")
public class AdvertisingController {
    @Autowired
    private AdvertisingService advertisingService;

    /**
     * 获取所有
     * @return
     */
    @GetMapping
    public Result getAdvertising() {
        return Result
                .ok().data("list", advertisingService.getListAdvertising());
    }
    @GetMapping("/{id}")
    public Result getAdvertising(@PathVariable("id")String id) {
        return Result
                .ok().data("list", advertisingService.getListAdvertisingById(id));
    }

    @JwtToken
    @PutMapping
    public Result putAdvertising(@RequestBody @Validated AdvertisingDTO advertisingDTO) {
        int i = advertisingService.putAdvertising(advertisingDTO);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("修改失败");
    }

    @PostMapping
    @JwtToken
    public Result postAdvertising(@RequestBody @Validated AdvertisingDTO advertisingDTO) {
        int i = advertisingService.postAdvertising(advertisingDTO);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("创建失败");
    }

    @DeleteMapping("/{id}")
    @JwtToken
    public Result postAdvertising(@PathVariable("id") String id) {
        int i = advertisingService.delAdvertising(id);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("删除失败");
    }
}

