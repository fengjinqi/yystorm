package com.yystorm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.UserFollowsDTO;
import com.yystorm.entity.UserFollows;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yystorm.vo.UserFollowsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-24
 */
public interface UserFollowsService extends IService<UserFollows> {
    /**
     * 关注
     * @param userFollowsDTO
     * @return
     */
    int addUserFollows(UserFollowsDTO userFollowsDTO);

    int isUserFollows(UserFollowsDTO userFollowsDTO);

    /**
     * 查看用户的关注
     * @param page1
     * @param id
     * @return
     */
    IPage<UserFollowsVO> slectPageMap(Page<UserFollowsVO> page1, String id);

    /**
     * 取消关注
     * @param id
     * @return
     */
    int delUserFollows(String id);

    /**
     * 查看粉丝
     * @param page1
     * @param id
     * @return
     */
    IPage<UserFollowsVO> slectPageFanMap(Page<UserFollowsVO> page1, String id);
}
