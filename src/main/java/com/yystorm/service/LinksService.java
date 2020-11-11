package com.yystorm.service;

import com.yystorm.dto.AdvertisingDTO;
import com.yystorm.dto.LinksDTO;
import com.yystorm.entity.Advertising;
import com.yystorm.entity.Links;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-11
 */
public interface LinksService extends IService<Links> {
    List<Links> getListLinks();

    int putLinks(LinksDTO linksDTO);

    int postLinks(LinksDTO linksDTO);

    int delLinks(String id);

    Links getListLinksById(String id);
}
