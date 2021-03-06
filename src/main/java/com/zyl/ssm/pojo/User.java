package com.zyl.ssm.pojo;

import java.io.Serializable;


public class User implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 2235129137913289292L;
    private int id;
    private String userName;
    private int userAge;
    
    
    public User(int id, String userName, int userAge) {
        super();
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
    }
    public User() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", userAge=" + userAge + "]";
    }
}
