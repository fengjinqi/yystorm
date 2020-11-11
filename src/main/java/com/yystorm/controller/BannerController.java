package com.yystorm.controller;


import com.yystorm.dto.BannerDTO;
import com.yystorm.dto.LinksDTO;
import com.yystorm.handler.JwtToken;
import com.yystorm.service.BannerService;
import com.yystorm.service.LinksService;
import com.yystorm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 获取所有
     * @return
     */
    @GetMapping
    public Result getAdvertising() {
        return Result
                .ok().data("list", bannerService.getListBanner());
    }
    @GetMapping("/{id}")
    public Result getAdvertising(@PathVariable("id")String id) {
        return Result
                .ok().data("list", bannerService.getListBannerById(id));
    }

    @JwtToken
    @PutMapping
    public Result putAdvertising(@RequestBody @Validated BannerDTO BannerDTO) {
        int i = bannerService.putBanner(BannerDTO);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("修改失败");
    }

    @PostMapping
    @JwtToken
    public Result postAdvertising(@RequestBody @Validated BannerDTO BannerDTO) {
        int i = bannerService.postBanner(BannerDTO);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("创建失败");
    }

    @DeleteMapping("/{id}")
    @JwtToken
    public Result postAdvertising(@PathVariable("id") String id) {
        int i = bannerService.delBanner(id);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("删除失败");
    }
}

