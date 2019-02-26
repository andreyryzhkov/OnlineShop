package com.aryzhkov.onlineshop.dao.jdbc;

import com.aryzhkov.onlineshop.dao.IUserDao;
import com.aryzhkov.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUserDao {
    private static final String GET_USER_BY_NAME_SQL = "SELECT \"USERNAME\" as username, \"PASSWORD\" as password," +
            "\"USERTYPE\" as usertype, \"ID\" as id, \"SALT\" as salt FROM public.\"USERS\"  WHERE \"USERNAME\" = ?";

    private static final String INSERT_SQL = "insert into public.\"USERS\" (\"USERNAME\", \"PASSWORD\", \"USERTYPE\", \"SALT\") VALUES (?,?,?,?)";

//    private static final UserMapper USER_MAPPER = new UserMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByName(String userName) {
        return jdbcTemplate.queryForObject(GET_USER_BY_NAME_SQL, new Object[]{userName}, ROW_MAPPER);
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update(INSERT_SQL, user.getUserName(), user.getPassword(), user.getUserType().getName(), user.getSalt());
    }
/**
 @Override public User getUserByName(String userName) {
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

 @Override public void addUser(User user) {
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
 }*/
}