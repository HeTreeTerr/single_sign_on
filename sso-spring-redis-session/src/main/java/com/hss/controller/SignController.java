package com.hss.controller;

import com.github.pagehelper.PageInfo;
import com.hss.bean.User;
import com.hss.service.UserService;
import com.hss.util.Msg;
import com.hss.util.MyMD5Util;
import com.hss.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/sign")
public class SignController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password")String password
            , HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(userName != null && !"".equals(userName) && password != null && !"".equals(password)){
            //从数据库获取用户信息
            User user = userService.findUserByUserName(userName);
            if(user != null && MyMD5Util.validPassword(password, user.getPassWord())){
                // 登录成功,保存当前用户登录的sessionId
                String sessionID = session.getId();
                logger.info("sessionID------------->"+sessionID);
                User sessionUserInfo = createUserParam(user);
                session.setAttribute("userSession",sessionUserInfo);
                RedisUtil.set("spring:session:loginUser:" + user.getId(), session.getId(), 60*60*1000);
                return Msg.success().add("flag","true");
            }else{
                return Msg.fail().add("errorMsg","用户名不存在，或密码错误");
            }
        }else{
            return Msg.fail().add("errorMsg","用户名或密码为空");
        }
    }

    @RequestMapping(value = "/getUserSignInfo",method = {RequestMethod.POST,RequestMethod.GET})
    public Msg getUserInfo(HttpSession session) {

        User userInfo=(User) session.getAttribute("userSession");
        return Msg.success().add("userInfo",userInfo);
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.POST,RequestMethod.GET})
    public Msg logout(HttpSession session, SessionStatus sessionStatus){
        User userInfo=(User) session.getAttribute("userSession");
        RedisUtil.delete("spring:session:loginUser:" + userInfo.getId());
        session.invalidate();
        sessionStatus.setComplete();
        return Msg.success().add("flag","true");
    }

    private User createUserParam(User user){
        User sessionUserInfo = new User();
        sessionUserInfo.setId(user.getId());
        sessionUserInfo.setUserName(user.getUserName());
        sessionUserInfo.setName(user.getName());
        sessionUserInfo.setHeadImgUrl(user.getHeadImgUrl());
        sessionUserInfo.setSex(user.getSex());
        sessionUserInfo.setTfAdmin(user.getTfAdmin());
        return sessionUserInfo;
    }
}
