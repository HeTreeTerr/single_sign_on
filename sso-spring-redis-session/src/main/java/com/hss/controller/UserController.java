package com.hss.controller;

import com.github.pagehelper.PageInfo;
import com.hss.bean.User;
import com.hss.service.UserService;
import com.hss.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/finUserListPage",method = {RequestMethod.GET,RequestMethod.POST})
    public Msg finUserListPage(@RequestParam(value = "pageNo") Integer pageNo,@RequestParam(value = "pageSize") Integer pageSize){
        PageInfo<User> pageInfo = userService.findUserListPage(pageNo,pageSize);
        return Msg.success().add("pageInfo",pageInfo);
    }

    @RequestMapping(value = "/registeredUser",method = RequestMethod.POST)
    public Msg registeredUser(User user){
        try{
            Long userId = userService.registeredUser(user);
            return Msg.success().add("userId",userId);
        }catch (Exception e){
            String errorMsg = "用户注册失败";
            e.printStackTrace();
            logger.info("errorMsg:"+errorMsg);
            return Msg.fail().add("errorMsg",errorMsg);
        }
    }
}
