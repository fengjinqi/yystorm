package com.yystorm.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-23
 */
@Data

public class ArticleCommentDTO {
    @NotNull(message = "articleId不能为空")
    private String articleId;
    @NotNull(message = "userId不能为空")
    private Integer userId;
    @NotNull(message = "content不能为空")
    @Length(max = 140,message = "字数不能超过140字")
    private String content;

}
