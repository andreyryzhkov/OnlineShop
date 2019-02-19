package com.aryzhkov.onlineshop.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String userName;
    private byte[] password;
    private UserType userType;
    private byte[] salt;

    public User() {

    }

    public User(String userName, byte[] password, UserType userType, byte[] salt) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.salt = salt;
    }
}
