package com.hss.service.impl;

import com.hss.bean.User;
import com.hss.mapper.UserMapper;
import com.hss.service.TxUserService;
import com.hss.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxUserServiceImpl implements TxUserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updateUserSex(Long userId, Integer sex,Boolean tfAdmin) {
        logger.info("--------------事务一----------------");
        User user = new User();
        user.setId(userId);
        user.setSex(sex);
        userMapper.updateUserSex(user);
        if(false){
            throw new RuntimeException("事务一运行时异常");
        }
        userService.updateUserTfAdmin(userId,tfAdmin);
    }

}
