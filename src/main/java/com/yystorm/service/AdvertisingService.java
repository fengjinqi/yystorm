package com.yystorm.service;

import com.yystorm.dto.AdvertisingDTO;
import com.yystorm.entity.Advertising;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-09
 */
public interface AdvertisingService extends IService<Advertising> {

    List<Advertising> getListAdvertising();

    int putAdvertising(AdvertisingDTO advertisingDTO);

    int postAdvertising(AdvertisingDTO advertisingDTO);

    int delAdvertising(String id);

    Advertising getListAdvertisingById(String id);
}
