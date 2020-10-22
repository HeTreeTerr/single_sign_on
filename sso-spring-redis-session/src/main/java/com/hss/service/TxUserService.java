package com.hss.service;

public interface TxUserService {

    /**
     * 修改用户性别
     * @param user
     */
    void updateUserSex(Long userId,Integer sex,Boolean tfAdmin);

}
