package com.yystorm.service;

import com.yystorm.entity.Messages;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-28
 */
public interface MessagesService extends IService<Messages> {

    int createMessages(Messages messages);
}
