package com.yystorm.controller;


import com.yystorm.dto.LinksDTO;
import com.yystorm.dto.PartnerDTO;
import com.yystorm.handler.JwtToken;
import com.yystorm.service.LinksService;
import com.yystorm.service.PartnerService;
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
@RequestMapping("/partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    /**
     * 获取所有
     * @return
     */
    @GetMapping
    public Result getAdvertising() {
        return Result
                .ok().data("list", partnerService.getListPartner());
    }
    @GetMapping("/{id}")
    public Result getAdvertising(@PathVariable("id")String id) {
        return Result
                .ok().data("list", partnerService.getListPartnerById(id));
    }

    @JwtToken
    @PutMapping
    public Result putAdvertising(@RequestBody @Validated PartnerDTO partnerDTO) {
        int i = partnerService.putPartner(partnerDTO);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("修改失败");
    }

    @PostMapping
    @JwtToken
    public Result postAdvertising(@RequestBody @Validated PartnerDTO partnerDTO) {
        int i = partnerService.postPartner(partnerDTO);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("创建失败");
    }

    @DeleteMapping("/{id}")
    @JwtToken
    public Result postAdvertising(@PathVariable("id") String id) {
        int i = partnerService.delPartner(id);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error().message("删除失败");
    }
}

