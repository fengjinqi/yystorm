package com.yystorm.execptionhandler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//有参数构造
@NoArgsConstructor//无参数构造
public class GuliException extends RuntimeException {
    private Integer code;
    private String msg;
}
