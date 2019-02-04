package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.entity.User;

import java.util.List;

public interface IOnlineShopDao {

    public User getUser(String userName);
}
