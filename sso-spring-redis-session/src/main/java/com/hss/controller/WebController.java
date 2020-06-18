package com.hss.controller;

import com.hss.bean.User;
import com.hss.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/service")
public class WebController {

    @RequestMapping("/login")
    public String  login(HttpSession session) {
        User user = new User(1L,"张三");
        // 登录成功,保存当前用户登录的sessionId
        String sessionID = session.getId();
        System.out.println("sessionID------------->"+sessionID);
        session.setAttribute("userSession",user);
        RedisUtil.set("loginUser:" + user.getId(), session.getId(), 60*60*1000);
        return "ok";
    }

    @RequestMapping("/getUserInfo")
    public Object getUserInfo(HttpSession session) {

        User userInfo=(User) session.getAttribute("userSession");
        return userInfo;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "ok";
    }
}
