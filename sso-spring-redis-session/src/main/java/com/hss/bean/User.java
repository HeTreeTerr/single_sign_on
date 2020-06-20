package com.hss.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户信息
 */
public class User extends BaseDomain {

    public User() {
    }

    /** 用户名 */
    private String userName;
    /** 用户姓名 */
    private String name;
    /** 密码 */
    private String passWord;
    /** 手机号 */
    private String mobileNumber;
    /** 出生日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Date brithday;
    /** 性别 */
    private Integer sex;
    /** 头像 */
    private String headImgUrl;
    /** 是否管理员 **/
    private Boolean tfAdmin;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Boolean getTfAdmin() {
        return tfAdmin;
    }

    public void setTfAdmin(Boolean tfAdmin) {
        this.tfAdmin = tfAdmin;
    }
}
