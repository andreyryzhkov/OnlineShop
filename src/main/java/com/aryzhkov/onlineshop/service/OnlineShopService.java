package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.dao.OnlineShopDao;
import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.User;

import java.util.List;

public class OnlineShopService implements IOnlineShopService {

    private OnlineShopDao onlineShopDao;

    public OnlineShopService(OnlineShopDao onlineShopDao) {
        this.onlineShopDao = onlineShopDao;
    }

    @Override
    public User getUser(String userName) {
        return onlineShopDao.getUser(userName);
    }

    @Override
    public List<Product> getAllProduct() {
        return onlineShopDao.getAllProduct();
    }

    @Override
    public Product getProductById(int id) {
        return onlineShopDao.getProductById(id);
    }

    @Override
    public void deleteProduct(int id) {
        onlineShopDao.deleteProduct(id);
    }

    @Override
    public void insertProduct(Product product) {
        onlineShopDao.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        onlineShopDao.updateProduct(product);
    }
}
