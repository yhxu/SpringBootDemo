package com.xuyh.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @Author: XUYH
 * @Description: 实体模型
 * @Date: 2018/12/12
 * @Version:
 */
@Table(name = "USER")
@Entity
public class UserModel {
    @Id
    private String UserId     = "";
    @Column(nullable = false)
    private String UserName   = "";
    private int    UserAge    = 0;
    private String UserSex    = "";
    @Column(name = "UserCardId", length =18, unique = true)
    private String UserCardId = "";
    private String UserPhoneNumber = "";
    private String UserMail   = "";

    @Override
    public String toString() {
        return "UserModel{" +
                "UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserAge=" + UserAge +
                ", UserSex='" + UserSex + '\'' +
                ", UserCardId='" + UserCardId + '\'' +
                ", UserPhoneNumber='" + UserPhoneNumber + '\'' +
                ", UserMail='" + UserMail + '\'' +
                '}';
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getUserAge() {
        return UserAge;
    }

    public void setUserAge(int userAge) {
        UserAge = userAge;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public String getUserCardId() {
        return UserCardId;
    }

    public void setUserCardId(String userCardId) {
        UserCardId = userCardId;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public String getUserMail() {
        return UserMail;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (UserAge != userModel.UserAge) return false;
        if (!UserId.equals(userModel.UserId)) return false;
        if (!UserName.equals(userModel.UserName)) return false;
        return UserSex.equals(userModel.UserSex);
    }

    @Override
    public int hashCode() {
        int result = UserId.hashCode();
        result = 31 * result + UserName.hashCode();
        result = 31 * result + UserAge;
        result = 31 * result + UserSex.hashCode();
        return result;
    }
}
