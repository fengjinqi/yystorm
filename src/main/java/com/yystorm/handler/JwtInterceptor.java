package com.yystorm.handler;

import com.yystorm.entity.User;
import com.yystorm.execptionhandler.GuliException;
import com.yystorm.service.UserService;
import com.yystorm.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        // 从 http 请求头中取出 token

        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(JwtToken.class)) {
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);
            if (jwtToken.required()) {
                // 执行认证
                if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                    String token = requestTokenHeader.substring(7);
                    // 获取 token 中的 userId
                    String userId = jwtUtils.getUsernameFromToken(token);
                    System.out.println("用户id:" + userId);
                    // 验证 token
                    User userCount = userService.getUserInfo(userId);
                    Boolean aBoolean = jwtUtils.validateToken(token, userCount);
                    if (!aBoolean){
                        throw new GuliException(401, "登录已失效!请重新登录");
                    }
                }
                else {
                    System.out.println(httpServletRequest.getContextPath());
                    throw new GuliException(400, "未登录");
                }

                }
            }

        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
