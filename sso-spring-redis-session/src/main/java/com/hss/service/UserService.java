package com.hss.service;

import com.github.pagehelper.PageInfo;
import com.hss.bean.User;

public interface UserService {

    PageInfo<User> finUserListPage(Integer pageNo, int pageSize);
}
