package com.yystorm.execptionhandler;



import com.yystorm.utils.Result;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;

@ControllerAdvice
@Slf4j
public class execption {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("执行了全局异常"+e.getMessage());
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e) {
        log.error("getMessage{}",e.getMsg());
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
  @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result Validation(MethodArgumentNotValidException e) {
      BindingResult bindingResult = e.getBindingResult();

      String errorMesssage = "校验失败:";

      for (FieldError fieldError : bindingResult.getFieldErrors()) {
          errorMesssage += fieldError.getDefaultMessage() + ", ";
      }

      log.error("getMessage{}",e.getMessage());
        e.printStackTrace();
        return Result.error().code(400).message(errorMesssage);
    }



    /**
     * JWT过滤器效验异常
     * @param e
     * @return
     */
/*
    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public Result error(ServletException e) {

        e.printStackTrace();
        return Result.error().code(401).message(e.getMessage());
    }
*/


/*
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错了!";
        }
        return Result.error().code(401).message(msg);
    }
*/


}
