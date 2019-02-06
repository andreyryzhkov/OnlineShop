package com.aryzhkov.onlineshop.dao.mapper;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapper {

    public User mapRowUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("pass"));
        user.setUserType(resultSet.getString("usertype"));
        user.setId(resultSet.getInt("id"));

        return user;
    }

    public Product mapRowProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));

        return product;
    }
}
