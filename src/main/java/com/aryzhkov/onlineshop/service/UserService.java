package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.dao.jdbc.JdbcUserDao;
import com.aryzhkov.onlineshop.entity.User;

public class UserService implements IUserService {

    private JdbcUserDao jdbcUserDaoDao;

    public UserService(JdbcUserDao jdbcUserDaoDao) {
        this.jdbcUserDaoDao = jdbcUserDaoDao;
    }

    @Override
    public User getUserByName(String userName) {
        return jdbcUserDaoDao.getUserByName(userName);
    }
}
