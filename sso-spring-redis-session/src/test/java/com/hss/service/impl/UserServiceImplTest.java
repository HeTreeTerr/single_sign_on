package com.hss.service.impl;

import com.hss.bean.User;
import com.hss.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Test
    public void bulkUpdateUserSex() {
        logger.info("测试事务----------------start");
        userService.bulkUpdateUserSex();
        logger.info("测试事务----------------end");
    }

    @Test
    public void registeredUser(){
        User user = new User();
        user.setUserName("hss");
        user.setName("何森森");
        user.setBrithday(new Date());
        user.setHeadImgUrl("lalal.png");
        user.setMobileNumber("18628466845");
        user.setPassWord("11111111");
        user.setSex(1);
        user.setTfAdmin(false);
        user.setCreateUser("to_dou");
        user.setUpdateUser("to_dou");
        userService.registeredUser(user);
    }
}