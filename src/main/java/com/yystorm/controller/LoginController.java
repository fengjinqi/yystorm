package com.yystorm.controller;


import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.yystorm.entity.User;
import com.yystorm.service.UserService;
import com.yystorm.websocket.WebSocketServer;
import com.yystorm.utils.JwtUtils;
import com.yystorm.utils.Result;

import lombok.extern.slf4j.Slf4j;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;

import java.util.Map;


@RestController
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.appid}")
    private String appid;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/api/login")
    public void getLogin(String code, String state) throws IOException {
        log.info("code===>" + code);
        log.info("state===>" + state);
        ObjectMapper msg = new ObjectMapper();
        if (StringUtils.isEmpty(code)) WebSocketServer.sendInfo( msg.writeValueAsString(Result.error().data("msg","未授权")), state);
        String wxAccessToken = userService.getWxAccessToken(appid, secret, code);
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(wxAccessToken, Map.class);
        if (map.containsKey("openid") && map.containsKey("access_token")) {
            String openid = (String) map.get("openid");
            String access_token = (String) map.get("access_token");
            User userCount = userService.getUserInfo(openid);
            if (ObjectUtil.isNotEmpty(userCount)) {
                String token = jwtUtils.generateAccessToken(userCount);
                WebSocketServer.sendInfo( msg.writeValueAsString(Result.ok().data("token","Bearer " + token)), state);
                return;
            }
            String wxUserInfo = userService.getWxUserInfo(openid, access_token);
            ObjectMapper objectMapper1 = new ObjectMapper();
            Map map1 = objectMapper1.readValue(wxUserInfo, Map.class);
            if (map1.containsKey("unionid")) {
                String openid1 = (String) map1.get("openid");
                String username = (String) map1.get("nickname");
                String userImg = (String) map1.get("headimgurl");
                User user = new User();
                user.setOpenid(openid1);
                user.setUserImg(userImg);
                user.setUsername(username);
                int register = userService.register(user);
                if (register > 0) {
                    String token = jwtUtils.generateAccessToken(user);
                    WebSocketServer.sendInfo( msg.writeValueAsString(Result.ok().data("token","Bearer " + token)), state);
                }else{
                    WebSocketServer.sendInfo( msg.writeValueAsString(Result.error().data("msg","用户创建失败")), state);
                }
            }else{
                WebSocketServer.sendInfo( msg.writeValueAsString(Result.error().data("msg","未授权")), state);
            }
        }else {
            WebSocketServer.sendInfo( msg.writeValueAsString(Result.error().data("msg","无效授权")), state);
        }

    }

}
