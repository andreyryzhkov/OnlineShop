package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.entity.Product;

import java.util.List;

public interface IProductDao {
    public List<Product> getAllProduct();

    public Product getProductById(int id);

    public void deleteProduct(int id);

    public void insertProduct(Product product);

    public void updateProduct(Product product);
}
