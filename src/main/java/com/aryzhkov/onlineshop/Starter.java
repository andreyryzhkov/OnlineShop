package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.OnlineShopDao;
import com.aryzhkov.onlineshop.entity.User;

public class Starter {

    public static void main(String[] args) throws Exception {
        OnlineShopDao onlineShopDao = new OnlineShopDao();
        User user = onlineShopDao.getUser("avr");
    }
}
