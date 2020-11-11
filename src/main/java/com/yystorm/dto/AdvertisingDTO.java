package com.yystorm.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengjinqi
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Advertising对象", description="")
public class AdvertisingDTO implements Serializable {


    @ApiModelProperty(value = "id")
    private Integer id;

    @NotEmpty(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "外部地址")
    @NotEmpty(message = "外部地址不能为空")
    private String url;

    @ApiModelProperty(value = "图片地址")
    @NotEmpty(message = "图片地址不能为空")
    private String imgUrl;

}
