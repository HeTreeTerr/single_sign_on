package com.hss.service.impl;

import com.hss.bean.User;
import com.hss.service.UserService;
import com.hss.util.MyMD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    public void registeredUser() throws UnsupportedEncodingException, NoSuchAlgorithmException {
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

    @Test
    public void md5Encoding() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //获得password的加密后的密码：
        String encryptedPwd = MyMD5Util.getEncryptedPwd("3.14159293.14159293.14159293.1415929");
        logger.info("加密后的密码："+encryptedPwd+"  密码长度："+encryptedPwd.length());
        //输入值与加密值校验方法：
        logger.info(""+MyMD5Util.validPassword("BaiNaoHui456", encryptedPwd));
    }

    @Test
    public void findUserByUserName(){
        String userName = "hesensen";
        User user = userService.findUserByUserName(userName);
        logger.info("user:"+user);
    }
}