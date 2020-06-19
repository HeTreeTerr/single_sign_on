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
}
