package team.csht.service;

import team.csht.entity.Good;
import team.csht.util.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodService {
    public boolean checkGood(Good good) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM goods WHERE id=?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, good.getId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码07");
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
                JOptionPane.showMessageDialog(null, "错误码08");
                e.printStackTrace();
            }
        }
        return false;
    }
    public Good[] getGoodList() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM goods ORDER BY id";
        Good[] goodList = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int length = resultSet.getRow();

            for (int i = 0; i < length; i ++) {
                goodList[i].setId(resultSet.getInt("id"));
                goodList[i].setName(resultSet.getString("name"));
                goodList[i].setMerchant(resultSet.getString("merchant"));
                goodList[i].setBuyer(resultSet.getString("buyer"));
                goodList[i].setPrice(resultSet.getFloat("price"));
                goodList[i].setExistence(resultSet.getBoolean("existence"));
                resultSet.next();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码09");
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
                JOptionPane.showMessageDialog(null, "错误码08");
                e.printStackTrace();
            }
        }
        return goodList;
    }

    public boolean addGood(Good good) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
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
            JOptionPane.showMessageDialog(null, "错误码91");
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
        return checkGood(good);
    }

    public boolean buyGood(Good good) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        String sql = "UPDATE goods SET buyer =?, existence=? WHERE id=?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, good.getBuyer());
            statement.setInt(2, good.isExistence() ? 1 : 0);
            statement.setInt(3, good.getId());
            statement.executeUpdate();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码90");
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
        return good.isExistence();
    }

    public Good[] searchGood(String keyword) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM goods WHERE name LIKE ? ORDER BY id";
        String searchWord = "%" + keyword + "%";
        Good[] goodList = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, searchWord);
            resultSet = statement.executeQuery();
            int length = resultSet.getRow();

            for (int i = 0; i < length; i ++) {
                goodList[i].setId(resultSet.getInt("id"));
                goodList[i].setName(resultSet.getString("name"));
                goodList[i].setMerchant(resultSet.getString("merchant"));
                goodList[i].setBuyer(resultSet.getString("buyer"));
                goodList[i].setPrice(resultSet.getFloat("price"));
                goodList[i].setExistence(resultSet.getBoolean("existence"));
                resultSet.next();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码92");
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
                JOptionPane.showMessageDialog(null, "错误码94");
                e.printStackTrace();
            }
        }
        return goodList;
    }
}