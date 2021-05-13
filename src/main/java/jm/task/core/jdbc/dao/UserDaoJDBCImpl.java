package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS users " +
            "(Id INT AUTO_INCREMENT, Age INT, FirstName NVARCHAR(20), LastName NVARCHAR(20), Primary key (Id))";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS users;";
    private static final String CREATE_QUERY = "INSERT INTO users (`FirstName`,`LastName`,`Age`) VALUES (?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE Id = ?";
    private static final String FIND_ALL_QUERY = "SELECT Id, Age, FirstName, LastName FROM users";
    private static final String DELETE_ALL_QUERY = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_QUERY)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DROP_TABLE_QUERY)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(User.builder()
                        .id(resultSet.getLong("Id"))
                        .name(resultSet.getString("FirstName"))
                        .lastName(resultSet.getString("LastName"))
                        .age(resultSet.getByte("Age"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_QUERY)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
