package com.aryzhkov.onlineshop.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Session {
    private String token;
    private User user;
    private LocalDateTime expireDate;
}
