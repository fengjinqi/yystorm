package com.yystorm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.entity.Messages;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yystorm.vo.MessagesVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-28
 */
public interface MessagesService extends IService<Messages> {
    /**
     * 创建消息
     * @param messages
     * @return
     */
    int createMessages(Messages messages);

    /**
     * 获取消息
     * @param id
     * @return
     */
    IPage<MessagesVO> getMessagesList(Page<MessagesVO> page, Integer id);
}
