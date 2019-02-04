package com.aryzhkov.onlineshop.dao.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class PropertiesConnection {
    private static final String PROPERTIES_PATH = "db.properties";

    static Properties getProperties() {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fileInputStream);

            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}