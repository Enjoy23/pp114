package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public static final Connection connection = Util.getConnection();

    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS User ( " +
            "id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(25), " +
            "last_name VARCHAR(25), " +
            "age TINYINT UNSIGNED )";

    static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS User";
    static final String ADD_NEW_USER = "INSERT INTO user(name, last_name, age) VALUES (?, ?, ?)";
    static final String REMOVE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    static final String GET_ALL_USERS = "SELECT * FROM user";
    static final String CLEAN_USERS_TABLE = "DELETE FROM user";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_USER_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_USER_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER)) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery(GET_ALL_USERS)) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_USERS_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
