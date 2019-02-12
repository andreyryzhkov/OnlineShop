package com.aryzhkov.onlineshop.dao.jdbc;

import com.aryzhkov.onlineshop.dao.IUserDao;
import com.aryzhkov.onlineshop.dao.jdbc.mapper.UserMapper;
import com.aryzhkov.onlineshop.entity.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao implements IUserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getUserByName(String userName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT \"USERNAME\" as username, \"PASSWORD\" as pass," +
                     "\"USERTYPE\" as usertype, \"ID\" as id FROM public.\"USERS\"  WHERE \"USERNAME\" = ?")) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {


                if (!resultSet.next()) {
                    throw new SQLException("User is not found");
                }
                User user = USER_MAPPER.mapRowUser(resultSet);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}