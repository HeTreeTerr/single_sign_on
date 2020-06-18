package com.hss.bean;

import java.util.Date;

/**
 * 用户信息
 */
public class User extends BaseDomain {
    /** 用户名 */
    private String userName;
    /** 用户姓名 */
    private String name;
    /** 密码 */
    private String passWord;
    /** 手机号 */
    private String mobileNumber;
    /** 出生日期 */
    private Date brithday;
    /** 性别 */
    private String sex;
    /** 头像 */
    private String headImgUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
