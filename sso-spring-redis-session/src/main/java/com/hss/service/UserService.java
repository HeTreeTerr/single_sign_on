package com.hss.service;

import com.github.pagehelper.PageInfo;
import com.hss.bean.User;

import java.util.List;

public interface UserService {

    PageInfo<User> finUserListPage(Integer pageNo, int pageSize);

    void bulkUpdateUserSex();

    Long registeredUser(User user);
}
