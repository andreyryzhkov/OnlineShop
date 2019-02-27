/**
package com.aryzhkov.onlineshop.dao.jdbc.datasource;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class PGSDataSource {
    Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public DataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(properties.getProperty("db.url"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
        return dataSource;
    }
}
 */