package com.hss.controller;

import com.github.pagehelper.PageInfo;
import com.hss.bean.User;
import com.hss.service.UserService;
import com.hss.util.Msg;
import com.hss.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/sign")
public class SignController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/login")
    public Msg login(HttpSession session) {
        User user = new User();
        user.setId(1L);
        user.setUserName("张三");
        // 登录成功,保存当前用户登录的sessionId
        String sessionID = session.getId();
        System.out.println("sessionID------------->"+sessionID);
        session.setAttribute("userSession",user);
        RedisUtil.set("spring:session:loginUser:" + user.getId(), session.getId(), 60*60*1000);
        return Msg.success().add("flag","true");
    }

    @RequestMapping("/getUserSignInfo")
    public Msg getUserInfo(HttpSession session) {

        User userInfo=(User) session.getAttribute("userSession");
        logger.info(userInfo.getUserName());
        return Msg.success().add("userInfo",userInfo);
    }

    @RequestMapping("/logout")
    public Msg logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return Msg.success().add("flag","true");
    }

}
