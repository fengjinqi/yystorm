package com.yystorm.utils;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    //@ApiModelProperty(value = "是否成功")
    private Boolean success;

   // @ApiModelProperty(value = "返回状态")
    private Integer code;

   // @ApiModelProperty(value = "返回消息")
    private String message;

   // @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    ;

    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        result.setSuccess(true);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        result.setSuccess(false);
        return result;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
