package team.csht.service;

import team.csht.entity.Good;
import team.csht.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodService {
    public boolean checkGood(String name, String merchant) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM goods where name=? and merchant=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, merchant);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean addGood(Good good) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO goods (name, price, merchant, existence) values (?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, good.getName());
            statement.setDouble(2, good.getPrice());
            statement.setString(3, good.getMerchant());
            int existence = good.isExistence() ? 1 : 0;
            statement.setInt(4, existence);
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
        return this.checkGood(good.getName(), good.getMerchant());
    }
}
