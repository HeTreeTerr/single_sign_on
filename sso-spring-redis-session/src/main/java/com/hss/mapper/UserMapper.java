package com.hss.mapper;

import com.hss.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 由用户名查找用户信息
     * @param userName
     * @return
     */
    User findUserByUserName(@Param(value = "userName") String userName);
}
