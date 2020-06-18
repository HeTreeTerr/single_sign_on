package com.hss.controller;

import com.hss.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/service")
public class WebController {

    @RequestMapping("/login")
    public String  getMsg(HttpSession session) {
        User user = new User(1L,"张三");
        session.setAttribute("userSession", user);
        return "ok";
    }

    @RequestMapping("/getUserInfo")
    public Object  setMsg(HttpSession session) {

        User userInfo=(User) session.getAttribute("userSession");
        return userInfo;
    }

    @RequestMapping("/logout")
    public String invalidate(HttpSession session, SessionStatus sessionStatus){

        session.invalidate();
        sessionStatus.setComplete();
        return "ok";
    }
}
