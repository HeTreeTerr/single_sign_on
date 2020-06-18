package com.hss.bean;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private String userName;

    private String passWord;

    public User() {
    }

    public User(Long id,String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
