package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.dao.jdbc.UserDao;
import com.aryzhkov.onlineshop.entity.User;

public class UserService implements IUserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
