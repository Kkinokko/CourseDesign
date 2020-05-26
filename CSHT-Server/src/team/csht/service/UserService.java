package team.csht.service;

import team.csht.entity.User;
import team.csht.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** @author MnAs & Fe */
public class UserService {
    public boolean checkUser(User user) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from users where username=? and password=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean checkUsername(User user) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from users where username=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean addUser(User user) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO users (username, password) VALUES (?, ?);";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        return this.checkUser(user);
    }
}