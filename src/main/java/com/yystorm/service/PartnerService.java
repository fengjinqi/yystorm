package com.yystorm.service;

import com.yystorm.dto.PartnerDTO;
import com.yystorm.entity.Partner;
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
public interface PartnerService extends IService<Partner> {
    List<Partner> getListPartner();

    int putPartner(PartnerDTO partnerDTO);

    int postPartner(PartnerDTO partnerDTO);

    int delPartner(String id);

    Partner getListPartnerById(String id);
}
