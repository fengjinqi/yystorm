package com.yystorm.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.entity.Messages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystorm.vo.MessagesVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-28
 */
public interface MessagesMapper extends BaseMapper<Messages> {

    Page<MessagesVO> getMessagesList(Page<MessagesVO> page,Integer id);
}
