package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.User;

import java.util.List;

public interface IOnlineShopDao {

    public User getUser(String userName);

    public List<Product> getAllProduct();

    public Product getProductById(int id);

    public void deleteProduct(int id);

    public void insertProduct(Product product);

    public void updateProduct(Product product);
}
