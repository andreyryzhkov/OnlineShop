package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.dao.jdbc.JdbcUserDao;
import com.aryzhkov.onlineshop.dao.jdbc.connection.JdbcConnection;
import com.aryzhkov.onlineshop.entity.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class OnlineShopDaoTest {

    @Test
    public void getUserByName() throws IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
        }

        JdbcConnection jdbcConnection = new JdbcConnection();
        jdbcConnection.setProperties(properties);

        JdbcUserDao jdbcUserDao = new JdbcUserDao(jdbcConnection);
        User user = jdbcUserDao.getUserById(1);
    }
/**
 @Test public void getUserById() {
 OnlineShopDao onlineShopDao = new OnlineShopDao();
 onlineShopDao.getUserById(1);
 }

 @Test public void getAllProduct() {
 OnlineShopDao onlineShopDao = new OnlineShopDao();
 onlineShopDao.getAllProduct();
 }

 @Test public void getProductById() {
 OnlineShopDao onlineShopDao = new OnlineShopDao();
 onlineShopDao.getProductById(1);
 }
 */
}