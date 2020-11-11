package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.entity.Messages;
import com.yystorm.mapper.MessagesMapper;
import com.yystorm.service.MessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.vo.MessagesVO;
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

    /**
     * 获取消息列表
     * @param id
     * @return
     */
    @Override
    public IPage<MessagesVO> getMessagesList(Page<MessagesVO> page,Integer id) {
        return baseMapper.getMessagesList(page,id);
    }
}
