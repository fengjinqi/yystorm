package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yystorm.dto.AdvertisingDTO;
import com.yystorm.entity.Advertising;
import com.yystorm.mapper.AdvertisingMapper;
import com.yystorm.service.AdvertisingService;
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
 * @since 2020-11-09
 */
@Service
public class AdvertisingServiceImpl extends ServiceImpl<AdvertisingMapper, Advertising> implements AdvertisingService {

    @Override
    public List<Advertising> getListAdvertising() {
        return baseMapper.selectList(null);
    }

    @Override
    public int putAdvertising(AdvertisingDTO advertisingDTO) {
        Advertising advertising = new Advertising();
        BeanUtils.copyProperties(advertisingDTO,advertising);

        return baseMapper.updateById(advertising);
    }

    @Override
    public int postAdvertising(AdvertisingDTO advertisingDTO) {
        Advertising advertising = new Advertising();
        BeanUtils.copyProperties(advertisingDTO,advertising);
        return baseMapper.insert(advertising);
    }

    @Override
    public int delAdvertising(String id) {
        return baseMapper.delete(new QueryWrapper<Advertising>().eq("id",id));
    }

    @Override
    public Advertising getListAdvertisingById(String id) {

        return baseMapper.selectOne(new QueryWrapper<Advertising>().eq("id",id));
    }
}
