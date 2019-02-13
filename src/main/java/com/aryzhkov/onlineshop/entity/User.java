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
}
