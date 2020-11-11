package com.yystorm.service;

import com.yystorm.dto.BannerDTO;
import com.yystorm.dto.LinksDTO;
import com.yystorm.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yystorm.entity.Links;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-11
 */
public interface BannerService extends IService<Banner> {
    List<Banner> getListBanner();

    int putBanner(BannerDTO bannerDTO);

    int postBanner(BannerDTO bannerDTO);

    int delBanner(String id);

    Banner getListBannerById(String id);
}
