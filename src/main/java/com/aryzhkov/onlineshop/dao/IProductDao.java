package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface IProductDao {
    RowMapper<Product> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Product(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getDouble("price"),
            resultSet.getDate("datemaking").toLocalDate());

    public List<Product> getAllProduct();

    public Product getProductById(int id);

    public void deleteProduct(int id);

    public void insertProduct(Product product);

    public void updateProduct(Product product);
}
