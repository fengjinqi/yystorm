package com.yystorm.service;

import com.yystorm.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-10
 */
public interface UserRoleService extends IService<UserRole> {
    int createUserRole(Integer uId,Integer rId);
}
