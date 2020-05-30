package team.csht.service;

import team.csht.entity.Good;
import team.csht.util.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodService {
    public Good[] getGoodList() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM goods";
        Good[] goodList = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int length = resultSet.getRow();

            for (int i = 0; i < length; i ++) {
                goodList[i].setName(resultSet.getString("name"));
                goodList[i].setMerchant(resultSet.getString("merchant"));
                goodList[i].setBuyer(resultSet.getString("buyer"));
                goodList[i].setPrice(resultSet.getFloat("price"));
                goodList[i].setExistence(resultSet.getBoolean("existence"));
                resultSet.next();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码05");
            e.printStackTrace();
        }

        return goodList;
    }
    public boolean checkGood(Good g) {
        return g.isExistence();
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
        return false;
    }

    public boolean deleteGood(Good good) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO goods (name, price, merchant, existence) values (?, ?, ?, ?)";//要改
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
        return false;
    }
}
