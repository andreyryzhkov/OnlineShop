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

    public User() {
    }

    public User(int id, String userName, String password, String userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }
}
