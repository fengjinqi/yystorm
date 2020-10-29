package com.yystorm.service.impl;

import com.yystorm.entity.Messages;
import com.yystorm.mapper.MessagesMapper;
import com.yystorm.service.MessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-28
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {

    @Override
    public int createMessages(Messages messages) {

        return baseMapper.insert(messages);
    }
}
