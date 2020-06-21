package com.hss.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hss.bean.User;
import com.hss.mapper.UserMapper;
import com.hss.service.UserService;
import com.hss.util.MyMD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findUserListPage(Integer pageNo, int pageSize) {
        //Mapper接口方式的调用
        PageHelper.startPage(pageNo,pageSize);
        List<User> userList = userMapper.findUserList();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
    }

    @Override
    public void bulkUpdateUserSex() {
        User user5 = new User();
        user5.setId(5L);
        user5.setSex(1);
        userMapper.updateUserInfo(user5);
        if(true){
            throw new RuntimeException("我是故意的异常");
        }
        User user6 = new User();
        user6.setId(6L);
        user6.setSex(1);
        userMapper.updateUserInfo(user6);
    }

    @Override
    public Long registeredUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //密码明文加密
        user.setPassWord(MyMD5Util.getEncryptedPwd(user.getPassWord()));
        user.setCreateUser("admin");
        user.setUpdateUser("admin");
        userMapper.registeredUser(user);
        logger.info("registeredUser id------------>"+user.getId());
        return user.getId();
    }

    @Override
    public User findUserByUserName(String userName) {

        return userMapper.findUserByUserName(userName);
    }
}
