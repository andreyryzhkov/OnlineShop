package com.aryzhkov.onlineshop.dao.jdbc;

import com.aryzhkov.onlineshop.dao.IJdbcUserDao;
import com.aryzhkov.onlineshop.dao.jdbc.connection.JdbcConnection;
import com.aryzhkov.onlineshop.dao.jdbc.mapper.UserMapper;
import com.aryzhkov.onlineshop.entity.User;

import java.sql.*;

public class JdbcUserDao implements IJdbcUserDao {
    private JdbcConnection jdbcConnection;

    public JdbcUserDao(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    @Override
    public User getUserByName(String userName) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT \"USERNAME\" as username, \"PASSWORD\" as pass, \"USERTYPE\" as usertype, \"ID\" as id FROM public.\"USERS\"  WHERE \"USERNAME\" = ?");
        ) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            UserMapper userMapper = new UserMapper();
            if (!resultSet.next()) {
                throw new SQLException("User is not found");
            }
            User user = userMapper.mapRowUser(resultSet);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}