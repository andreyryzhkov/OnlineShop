package com.aryzhkov.onlineshop.dao.jdbc;

import com.aryzhkov.onlineshop.dao.IProductDao;
import com.aryzhkov.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao implements IProductDao {
    //  private static final ProductMapper PRODUCT_ROW_MAPPER = new ProductMapper();

    private static final String SELECT_ALL_PRODUCT = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price, \"DATEMAKING\" as datemaking FROM onlineshop.PRODUCT";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price, \"DATEMAKING\" as datemaking FROM onlineshop.PRODUCT WHERE \"ID\" = ?";
    private static final String INSERT_PRODUCT = "INSERT INTO onlineshop.PRODUCT(\"NAME\", \"PRICE\", \"DATEMAKING\") VALUES (?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE onlineshop.PRODUCT SET \"NAME\" = ?, \"PRICE\" = ? WHERE \"ID\" = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM onlineshop.PRODUCT WHERE \"ID\" = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProduct() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCT, ROW_MAPPER);
    }

    @Override
    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT, id);
    }

    @Override
    public void insertProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT, product.getName(), product.getPrice(), product.getDateMaking());
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT, product.getName(), product.getPrice(), product.getId());
    }
}