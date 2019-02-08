package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.entity.User;

public interface IJdbcUserDao {

    public User getUserByName(String userName);

    public User getUserById(int id);
}
