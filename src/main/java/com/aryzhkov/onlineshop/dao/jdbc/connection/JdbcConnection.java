package com.aryzhkov.onlineshop.dao.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {
    Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}