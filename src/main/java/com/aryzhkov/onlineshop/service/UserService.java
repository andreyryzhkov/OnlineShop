package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.dao.jdbc.UserDao;
import com.aryzhkov.onlineshop.entity.User;

public class UserService implements IUserService {

    private UserDao jdbcUserDaoDao;

    public UserService(UserDao jdbcUserDaoDao) {
        this.jdbcUserDaoDao = jdbcUserDaoDao;
    }

    @Override
    public User getUserByName(String userName) {
        return jdbcUserDaoDao.getUserByName(userName);
    }

    @Override
    public void addUser(User user) {
        jdbcUserDaoDao.addUser(user);
    }
}
