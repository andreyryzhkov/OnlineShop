package com.aryzhkov.onlineshop.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class OnlineShopDaoTest {

    @Test
    public void getUserByName() {
        OnlineShopDao onlineShopDao = new OnlineShopDao();
        onlineShopDao.getUserByName("user1");
    }

    @Test
    public void getUserById() {
        OnlineShopDao onlineShopDao = new OnlineShopDao();
        onlineShopDao.getUserById(1);
    }

    @Test
    public void getAllProduct() {
        OnlineShopDao onlineShopDao = new OnlineShopDao();
        onlineShopDao.getAllProduct();
    }

    @Test
    public void getProductById() {
        OnlineShopDao onlineShopDao = new OnlineShopDao();
        onlineShopDao.getProductById(1);
    }
}