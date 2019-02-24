package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.jdbc.ProductDao;
import com.aryzhkov.onlineshop.dao.jdbc.datasource.PGSDataSource;
import com.aryzhkov.onlineshop.service.ProductService;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {
    private static final String PROPERTIES_PATH = "db.properties";

    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {

        Properties properties = new Properties();

        try (InputStream inputStream = ServiceLocator.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH)) { //error path
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PGSDataSource pgsDataSource = new PGSDataSource();
        pgsDataSource.setProperties(properties);
        DataSource dataSource = pgsDataSource.createDataSource();

        ProductDao productDao = new ProductDao(dataSource);
        ProductService productService = new ProductService(productDao);

        SERVICES.put(ProductService.class, productService);
    }

    public static <T> T getService(Class<T> clazz) {
        return clazz.cast(SERVICES.get(clazz));
    }
}