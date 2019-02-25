package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.dao.jdbc.ProductDao;
import com.aryzhkov.onlineshop.entity.Product;

import java.util.List;

public class ProductService implements IProductService {

    private ProductDao jdbcProductDao;

    @Override
    public List<Product> getAllProduct() {
        return jdbcProductDao.getAllProduct();
    }

    @Override
    public Product getProductById(int id) {
        return jdbcProductDao.getProductById(id);
    }

    @Override
    public void deleteProduct(int id) {
        jdbcProductDao.deleteProduct(id);
    }

    @Override
    public void insertProduct(Product product) {
        jdbcProductDao.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        jdbcProductDao.updateProduct(product);
    }

    public void setProductDao(ProductDao productDao) {
        this.jdbcProductDao = productDao;
    }
}
