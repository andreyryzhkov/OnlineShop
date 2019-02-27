/**
package com.aryzhkov.onlineshop.dao.jdbc.mapper;

import com.aryzhkov.onlineshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    public Product mapRowProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setDateMaking(resultSet.getDate("datemaking").toLocalDate());

        return product;
    }
}
*/