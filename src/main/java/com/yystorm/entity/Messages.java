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
@ApiModel(value="Messages对象", description="")
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "通知标题")
    private String title;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "跳转id")
    private String contentId;

    @ApiModelProperty(value = "0 正常 1删除")
    private Boolean enable;

    @ApiModelProperty(value = "创建消息")
    private Integer createId;

    @ApiModelProperty(value = "接收人ID 0-所有人 大于0就是用户id")
    private Integer acceptId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;


}
