package com.yystorm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.entity.UserFollows;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystorm.vo.UserFollowsVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-24
 */
public interface UserFollowsMapper extends BaseMapper<UserFollows> {

    IPage<UserFollowsVO> slectPageMap(Page<UserFollowsVO> page1, String id);

    IPage<UserFollowsVO> slectPageFanMap(Page<UserFollowsVO> page1, String id);
}
