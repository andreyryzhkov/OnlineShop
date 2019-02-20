package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.Starter;
import com.aryzhkov.onlineshop.dao.jdbc.ProductDao;
import com.aryzhkov.onlineshop.dao.jdbc.UserDao;
import com.aryzhkov.onlineshop.dao.jdbc.datasource.PGSDataSource;
import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;
import com.aryzhkov.onlineshop.service.SecurityService;
import com.aryzhkov.onlineshop.service.UserService;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;

public class OnlineShopDaoTest {

    @Test
    public void testGetUserByName() throws IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
        }

        PGSDataSource pgsDataSource = new PGSDataSource();
        pgsDataSource.setProperties(properties);
        DataSource dataSource = pgsDataSource.createDataSource();
        UserDao jdbcUserDao = new UserDao(dataSource);
        User user = jdbcUserDao.getUserByName("user1");
    }

    @Test
    public void testAddUser() throws IOException, NoSuchAlgorithmException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
        }

        PGSDataSource pgsDataSource = new PGSDataSource();
        pgsDataSource.setProperties(properties);
        DataSource dataSource = pgsDataSource.createDataSource();
        UserDao jdbcUserDao = new UserDao(dataSource);
        UserService userService = new UserService(jdbcUserDao);
        SecurityService securityService = new SecurityService(userService);

        String login = "user2";
        String password = "user2";
        User user = securityService.newUser(login, password, UserType.USER);
    }

    @Test
    public void testGetAllProduct() throws IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
        }

        PGSDataSource pgsDataSource = new PGSDataSource();
        pgsDataSource.setProperties(properties);
        DataSource dataSource = pgsDataSource.createDataSource();

        ProductDao jdbcProductDao = new ProductDao(dataSource);
        List<Product> products = jdbcProductDao.getAllProduct();
    }

    @Test
    public void getProductById() throws IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
        }

        PGSDataSource pgsDataSource = new PGSDataSource();
        pgsDataSource.setProperties(properties);
        DataSource dataSource = pgsDataSource.createDataSource();

        ProductDao jdbcProductDao = new ProductDao(dataSource);
        Product product = jdbcProductDao.getProductById(7);
    }
}