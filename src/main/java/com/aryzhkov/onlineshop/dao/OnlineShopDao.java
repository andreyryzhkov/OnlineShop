package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.dao.connection.JDBCConnection;
import com.aryzhkov.onlineshop.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnlineShopDao implements IOnlineShopDao {

    @Override
    public User getUser(String userName) {
        String selectSQL = "SELECT \"USERNAME\" as username, \"PASSWORD\" as pass FROM PUBLIC. \"USERS\"  WHERE \"USERNAME\" = " + "'" + userName + "'";

        try (Connection connection = JDBCConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            User user = new User();
            while (resultSet.next()) {
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("pass"));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Getting user are failed", e);
        }
    }
}
