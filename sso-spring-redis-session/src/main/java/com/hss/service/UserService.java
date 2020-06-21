package com.hss.service;

import com.github.pagehelper.PageInfo;
import com.hss.bean.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    /**
     * 查询用户列表（分页）
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<User> findUserListPage(Integer pageNo, int pageSize);

    void bulkUpdateUserSex();

    /**
     * 用户注册
     * @param user
     * @return
     */
    Long registeredUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 由用户名查找用户信息
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);
}
