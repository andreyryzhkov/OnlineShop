package com.aryzhkov.onlineshop.dao.jdbc;

import com.aryzhkov.onlineshop.dao.IJdbcProductDao;
import com.aryzhkov.onlineshop.dao.jdbc.connection.JdbcConnection;
import com.aryzhkov.onlineshop.dao.jdbc.mapper.ProductMapper;
import com.aryzhkov.onlineshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcProductDao implements IJdbcProductDao {
    private JdbcConnection jdbcConnection;

    public JdbcProductDao(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    @Override
    public List<Product> getAllProduct() {
        String selectSQL = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price FROM public.\"PRODUCT\"";

        try (Connection connection = jdbcConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            List<Product> products = new ArrayList<>();
            ProductMapper rowMapper = new ProductMapper();
            while (resultSet.next()) {
                Product product = rowMapper.mapRowProduct(resultSet);
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(int id) {
        String selectSQL = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price FROM public.\"PRODUCT\" WHERE \"ID\" = ?";

        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSQL)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            ProductMapper rowMapper = new ProductMapper();
            if (!resultSet.next()) {
                return null;
            }

            Product product = rowMapper.mapRowProduct(resultSet);
            return product;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        String deleteSQL = "DELETE FROM public.\"PRODUCT\" WHERE \"ID\" = " + id;

        try (Connection connection = jdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertProduct(Product product) {
        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO public.\"PRODUCT\"(\"NAME\", \"PRICE\") VALUES (");
        insertSQL.append("'").append(product.getName()).append("'").append(",");
        insertSQL.append("'").append(product.getPrice()).append("'").append(");");

        try (Connection connection = jdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertSQL.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE public.\"PRODUCT\" SET ");
        updateSQL.append("\"NAME\" = '").append(product.getName()).append("'").append(",");
        updateSQL.append("\"PRICE\" = '").append(product.getPrice()).append("'");
        updateSQL.append(" WHERE \"ID\" = ").append(product.getId());

        try (Connection connection = jdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateSQL.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}