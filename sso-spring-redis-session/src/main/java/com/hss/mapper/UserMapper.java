package com.hss.mapper;

import com.hss.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 统计用户列表
     * @return
     */
    List<User> findUserList();

    /**
     * 添加用户
     */
    void registeredUser(User user);

    /**
     * 修改用户信息
     */
    void updateUserInfo(User user);
}
