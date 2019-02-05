package com.aryzhkov.onlineshop;

import com.aryzhkov.onlineshop.dao.OnlineShopDao;
import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.User;

import java.util.List;

public class Starter {

    public static void main(String[] args) throws Exception {
        OnlineShopDao onlineShopDao = new OnlineShopDao();
        //  User user = onlineShopDao.getUser("ADMIN");
        // Product product = new Product(2,"Bike2", 1500);
        // onlineShopDao.updateProduct(product);

        List<Product> products = onlineShopDao.getAllProduct();

        // Product product = onlineShopDao.getProductById(2);

        //  onlineShopDao.insertProduct(product);
        //  onlineShopDao.deleteProduct(1);
    }
}
