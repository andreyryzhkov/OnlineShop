package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.dao.jdbc.JdbcProductDao;
import com.aryzhkov.onlineshop.dao.jdbc.JdbcUserDao;
import com.aryzhkov.onlineshop.dao.jdbc.connection.JdbcConnection;
import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class OnlineShopDaoTest {

    @Test
    public void testGetUserByName() throws IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
        }

        JdbcConnection jdbcConnection = new JdbcConnection();
        jdbcConnection.setProperties(properties);

        JdbcUserDao jdbcUserDao = new JdbcUserDao(jdbcConnection);
        User user = jdbcUserDao.getUserByName("user1");
    }

    @Test
    public void testGetAllProduct() throws IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
        }

        JdbcConnection jdbcConnection = new JdbcConnection();
        jdbcConnection.setProperties(properties);

        JdbcProductDao jdbcProductDao = new JdbcProductDao(jdbcConnection);
        List<Product> products = jdbcProductDao.getAllProduct();
    }

 @Test public void getProductById() throws IOException {
     Properties properties = new Properties();

     try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
         properties.load(fileInputStream);
     }

     JdbcConnection jdbcConnection = new JdbcConnection();
     jdbcConnection.setProperties(properties);

     JdbcProductDao jdbcProductDao = new JdbcProductDao(jdbcConnection);
     Product product = jdbcProductDao.getProductById(7);
  }

}