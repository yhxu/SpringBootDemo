package com.xuyh.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @Author: XUYH
 * @Description: 实体模型
 * @Date: 2018/12/12
 * @Version:
 */
@Table(name = "USER")
@Entity
public class UserModel implements Serializable{

    private String userId = "";
    private String userName = "";
    private int userAge;
    private String userSex = "";
    private String userCardId = "";
    private String userPhoneNumber = "";
    private String userMail = "";

    @Override
    public String toString() {
        return "UserModel{" +
                "UserId='" + userId + '\'' +
                ", UserName='" + userName + '\'' +
                ", UserAge=" + userAge +
                ", UserSex='" + userSex + '\'' +
                ", UserCardId='" + userCardId + '\'' +
                ", UserPhoneNumber='" + userPhoneNumber + '\'' +
                ", UserMail='" + userMail + '\'' +
                '}';
    }

    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Column(name = "UserCardId", length =18, unique = true)
    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserModel userModel = (UserModel) o;

        if (userAge != userModel.userAge) {
            return false;
        }
        if (!userId.equals(userModel.userId)) {
            return false;
        }
        if (!userName.equals(userModel.userName)) {
            return false;
        }
        return userSex.equals(userModel.userSex);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + userAge;
        result = 31 * result + userSex.hashCode();
        return result;
    }
}
