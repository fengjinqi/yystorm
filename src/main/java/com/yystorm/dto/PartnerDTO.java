package com.yystorm.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Partner对象", description="")
public class PartnerDTO implements Serializable {


    private Integer id;

    @ApiModelProperty(value = "name")
    @NotBlank(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "图片地址·")
    @NotBlank(message = "imgUrl不能为空")
    private String imgUrl;

    @ApiModelProperty(value = "访问地址")
    @NotBlank(message = "url不能为空")
    private String url;


}
