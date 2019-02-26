package com.aryzhkov.onlineshop.dao;

import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public interface IUserDao {
    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) ->
            new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getBytes("password"),
                    UserType.getByName(resultSet.getString("usertype")),
                    resultSet.getBytes("salt"));

    public User getUserByName(String userName);

    public void addUser(User user);
}
