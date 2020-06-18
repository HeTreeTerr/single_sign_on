package com.hss.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hss.bean.User;
import com.hss.mapper.UserMapper;
import com.hss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageInfo<User> finUserListPage(Integer pageNo, int pageSize) {
        //Mapper接口方式的调用
        PageHelper.startPage(pageNo,pageSize);
        List<User> userList = userMapper.findUserList();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
    }
}
