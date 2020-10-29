package com.yystorm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MessagesStatus对象", description="")
public class MessagesStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户操作的id")
    private Integer userId;

    @ApiModelProperty(value = "1 已读 0删除")
    private Boolean status;

    @ApiModelProperty(value = "消息id")
    private Integer messageId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;


}
