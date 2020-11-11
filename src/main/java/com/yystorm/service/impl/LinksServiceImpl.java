package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yystorm.dto.LinksDTO;
import com.yystorm.entity.Advertising;
import com.yystorm.entity.Links;
import com.yystorm.mapper.LinksMapper;
import com.yystorm.service.LinksService;
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
public class LinksServiceImpl extends ServiceImpl<LinksMapper, Links> implements LinksService {

    @Override
    public List<Links> getListLinks() {
        return baseMapper.selectList(null);
    }

    @Override
    public int putLinks(LinksDTO linksDTO) {
        Links links = new Links();
        BeanUtils.copyProperties(linksDTO,links);

        return baseMapper.updateById(links);
    }

    @Override
    public int postLinks(LinksDTO linksDTO) {
        Links links = new Links();
        BeanUtils.copyProperties(linksDTO,links);
        return baseMapper.insert(links);
    }

    @Override
    public int delLinks(String id) {
        return baseMapper.delete(new QueryWrapper<Links>().eq("id",id));
    }

    @Override
    public Links getListLinksById(String id) {
        return baseMapper.selectOne(new QueryWrapper<Links>().eq("id",id));
    }
}
