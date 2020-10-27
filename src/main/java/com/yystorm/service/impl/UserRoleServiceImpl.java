package com.yystorm.service.impl;

import com.yystorm.entity.UserRole;
import com.yystorm.mapper.UserRoleMapper;
import com.yystorm.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-10
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public int createUserRole(Integer uId, Integer rId) {
        UserRole userRole = new UserRole();
        userRole.setUId(uId);
        userRole.setRId(rId);
        return baseMapper.insert(userRole);
    }
}
