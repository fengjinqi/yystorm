package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.UserFollowsDTO;
import com.yystorm.entity.Messages;
import com.yystorm.entity.UserFollows;
import com.yystorm.mapper.UserFollowsMapper;
import com.yystorm.service.MessagesService;
import com.yystorm.service.UserFollowsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.utils.MessageEnum;
import com.yystorm.vo.UserFollowsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-24
 */
@Service
public class UserFollowsServiceImpl extends ServiceImpl<UserFollowsMapper, UserFollows> implements UserFollowsService {
    @Autowired
    private MessagesService messagesService;
    /**
     * 关注
     * @param userFollowsDTO
     * @return
     */
    @Override
    @Transactional
    public int addUserFollows(UserFollowsDTO userFollowsDTO) {
        UserFollows userFollows = new UserFollows();
        BeanUtils.copyProperties(userFollowsDTO, userFollows);
        Messages messages = new Messages();

        messages.setContent(String.format("%s关注了你","<span>"+userFollowsDTO.getUsername()+"</span>"));
        messages.setTitle(MessageEnum.FAN);
        messages.setAcceptId(userFollows.getFollowedUser());
        messages.setCreateId(userFollows.getUserId());
        messagesService.createMessages(messages);
        return baseMapper.insert(userFollows);

    }

    @Override
    public int isUserFollows(UserFollowsDTO userFollowsDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id",userFollowsDTO.getUserId());
        map.put("followed_user",userFollowsDTO.getFollowedUser());
        return baseMapper.selectCount(new QueryWrapper<UserFollows>().allEq(map));
    }

    /**
     * 查看用户的关注
     * @param page1
     * @param id
     * @return
     */
    @Override
    public IPage<UserFollowsVO> slectPageMap(Page<UserFollowsVO> page1, String id) {
        return baseMapper.slectPageMap(page1,id);
    }

    /**
     * 取消关注
     * @param id
     * @return
     */
    @Override
    public int delUserFollows(String id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 查询用户的粉丝
     * @param page1
     * @param id
     * @return
     */
    @Override
    public IPage<UserFollowsVO> slectPageFanMap(Page<UserFollowsVO> page1, String id) {
        return baseMapper.slectPageFanMap(page1,id);
    }
}
