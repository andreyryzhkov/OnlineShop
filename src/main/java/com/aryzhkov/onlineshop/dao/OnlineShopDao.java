package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.dao.connection.JDBCConnection;
import com.aryzhkov.onlineshop.dao.mapper.UserRowMapper;
import com.aryzhkov.onlineshop.entity.Product;
import com.aryzhkov.onlineshop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineShopDao implements IOnlineShopDao {

    public User getUser(String selectSQL) {
        try (Connection connection = JDBCConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            UserRowMapper userRowMapper = new UserRowMapper();
            User user = userRowMapper.mapRowUser(resultSet);

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByName(String userName) {
        String selectSQL = "SELECT \"USERNAME\" as username, \"PASSWORD\" as pass, \"USERTYPE\" as usertype, \"ID\" as id FROM public.\"USERS\"  WHERE \"USERNAME\" = " + "'" + userName + "'";
        return getUser(selectSQL);
    }

    @Override
    public User getUserById(int id) {
        String selectSQL = "SELECT \"USERNAME\" as username, \"PASSWORD\" as pass, \"USERTYPE\" as usertype, \"ID\" as id FROM public.\"USERS\"  WHERE \"ID\" = " + id;

        return getUser(selectSQL);
    }

    @Override
    public List<Product> getAllProduct() {
        String selectSQL = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price FROM public.\"PRODUCT\"";

        try (Connection connection = JDBCConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(int id) {
        String selectSQL = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price FROM public.\"PRODUCT\" WHERE \"ID\" = " + id;

        try (Connection connection = JDBCConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            Product product = new Product();
            while (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
            }
            return product;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        String deleteSQL = "DELETE FROM public.\"PRODUCT\" WHERE \"ID\" = " + id;

        try (Connection connection = JDBCConnection.getConnection();
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

        try (Connection connection = JDBCConnection.getConnection();
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

        try (Connection connection = JDBCConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateSQL.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}