package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yystorm.dto.PartnerDTO;
import com.yystorm.entity.Banner;
import com.yystorm.entity.Partner;
import com.yystorm.mapper.PartnerMapper;
import com.yystorm.service.PartnerService;
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
public class PartnerServiceImpl extends ServiceImpl<PartnerMapper, Partner> implements PartnerService {

    @Override
    public List<Partner> getListPartner() {
        return baseMapper.selectList(null);
    }

    @Override
    public int putPartner(PartnerDTO partnerDTO) {
        Partner partner = new Partner();
        BeanUtils.copyProperties(partnerDTO,partner);

        return baseMapper.updateById(partner);
    }

    @Override
    public int postPartner(PartnerDTO partnerDTO) {
        Partner partner = new Partner();
        BeanUtils.copyProperties(partnerDTO,partner);

        return baseMapper.insert(partner);
    }

    @Override
    public int delPartner(String id) {
        return baseMapper.delete(new QueryWrapper<Partner>().eq("id",id));
    }

    @Override
    public Partner getListPartnerById(String id) {
        return baseMapper.selectOne(new QueryWrapper<Partner>().eq("id",id));
    }
}
