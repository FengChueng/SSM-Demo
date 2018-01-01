package com.zyl.ssm.pojo;

import java.io.Serializable;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Scope(value="request",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserParam implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private String userName;
    private int userAge;
    
    
    public UserParam(String userName, int userAge) {
        super();
        this.userName = userName;
        this.userAge = userAge;
    }
    public UserParam() {
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
}
