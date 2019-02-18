package com.aryzhkov.onlineshop.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String userName;
    private String password;
    private String userType;
    private String salt;
    private byte[] saltBytes;

    public User() {

    }

    public User(String userName, String password, String userType, String salt, byte[] saltBytes) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.salt = salt;
        this.saltBytes = saltBytes;
    }
}
