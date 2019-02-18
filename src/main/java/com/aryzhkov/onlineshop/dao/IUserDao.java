package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.entity.User;

public interface IUserDao {

    public User getUserByName(String userName);

    public void addUser(User user);
}
