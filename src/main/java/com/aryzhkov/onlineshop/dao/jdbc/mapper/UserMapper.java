/**
package com.aryzhkov.onlineshop.dao.jdbc.mapper;

import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User mapRowUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getBytes("password"));
        user.setUserType(UserType.getByName(resultSet.getString("usertype")));
        user.setId(resultSet.getInt("id"));
        user.setSalt(resultSet.getBytes("salt"));

        return user;
    }
}
*/