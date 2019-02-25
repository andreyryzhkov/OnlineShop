package com.aryzhkov.onlineshop.dao.jdbc;

import com.aryzhkov.onlineshop.dao.IUserDao;
import com.aryzhkov.onlineshop.dao.jdbc.mapper.UserMapper;
import com.aryzhkov.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UserDao implements IUserDao {
    private static final String GET_USER_BY_NAME_SQL = "SELECT \"USERNAME\" as username, \"PASSWORD\" as password," +
            "\"USERTYPE\" as usertype, \"ID\" as id, \"SALT\" as salt FROM public.\"USERS\"  WHERE \"USERNAME\" = ?";

    private static final String INSERT_SQL = "insert into public.\"USERS\" (\"USERNAME\", \"PASSWORD\", \"USERTYPE\", \"SALT\") VALUES (?,?,?,?)";

    private static final UserMapper USER_MAPPER = new UserMapper();

    @Autowired
    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getUserByName(String userName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_NAME_SQL)) {
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

    @Override
    public void addUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, user.getUserName());
            statement.setBytes(2, user.getPassword());
            statement.setString(3, user.getUserType().getName());
            statement.setBytes(4, user.getSalt());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}