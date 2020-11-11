package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yystorm.dto.BannerDTO;
import com.yystorm.dto.LinksDTO;
import com.yystorm.entity.Banner;
import com.yystorm.entity.Links;
import com.yystorm.mapper.BannerMapper;
import com.yystorm.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-11
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Override
    public List<Banner> getListBanner() {
        return baseMapper.selectList(null);
    }

    @Override
    public int putBanner(BannerDTO bannerDTO) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(bannerDTO,banner);

        return baseMapper.updateById(banner);
    }

    @Override
    public int postBanner(BannerDTO bannerDTO) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(bannerDTO,banner);
        return baseMapper.insert(banner);
    }

    @Override
    public int delBanner(String id) {
        return baseMapper.delete(new QueryWrapper<Banner>().eq("id",id));
    }

    @Override
    public Banner getListBannerById(String id) {
        return baseMapper.selectOne(new QueryWrapper<Banner>().eq("id",id));
    }
}
