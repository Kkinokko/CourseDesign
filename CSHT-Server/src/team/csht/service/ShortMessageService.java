package team.csht.service;

import team.csht.entity.ShortMessage;
import team.csht.util.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class ShortMessageService {
    public boolean addShortMessage(ShortMessage shortMessage) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO messages (sender, receiver, content, time)" +
                "values (?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, shortMessage.getSender());
            statement.setString(2, shortMessage.getReceiver());
            statement.setString(3, shortMessage.getContent());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            statement.setTimestamp(4, timestamp);
            statement.executeUpdate();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码20");
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "错误码21");
                e.printStackTrace();
            }
        }
        return false;
    }

    public ShortMessage[] getShortMessage(String sender, String receiver) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM messages WHERE sender=? AND receiver=? " +
                "OR receiver=? AND sender=? ORDER BY number";
        ShortMessage[] shortMessageList = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, sender);
            statement.setString(2, receiver);
            statement.setString(3, receiver);
            statement.setString(4, sender);
            resultSet = statement.executeQuery();
            int length = resultSet.getRow();

            for (int i = 0; i < length; i ++) {
                shortMessageList[i].setNumber(resultSet.getInt("number"));
                shortMessageList[i].setSender(resultSet.getString("sender"));
                shortMessageList[i].setReceiver(resultSet.getString("receiver"));
                shortMessageList[i].setContent(resultSet.getString("content"));
                shortMessageList[i].setTime(resultSet.getTimestamp("time"));

                resultSet.next();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码22");
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
                JOptionPane.showMessageDialog(null, "错误码23");
                e.printStackTrace();
            }
        }

        return shortMessageList;
    }
}