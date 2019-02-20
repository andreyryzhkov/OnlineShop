package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.jdbc.UserDao;
import com.aryzhkov.onlineshop.dao.jdbc.datasource.PGSDataSource;
import com.aryzhkov.onlineshop.service.UserService;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {
    private static final String PROPERTIES_PATH = "db.properties";

    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PGSDataSource pgsDataSource = new PGSDataSource();
        pgsDataSource.setProperties(properties);
        DataSource dataSource = pgsDataSource.createDataSource();

        UserDao userDao = new UserDao(dataSource);
        UserService userService = new UserService(userDao);

        SERVICES.put(UserService.class, userService);
    }

    public static <T> T getService(Class<T> clazz) {
        return clazz.cast(SERVICES.get(clazz));
    }
}