package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.dao.jdbc.UserDao;
import com.aryzhkov.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
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
