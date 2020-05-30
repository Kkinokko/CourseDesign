package team.csht.service;

import team.csht.entity.Comment;
import team.csht.util.DatabaseConnection;

import javax.swing.*;
import java.sql.*;

/** @author MnAs & Fe */
public class CommentService {
    public boolean addComment(Comment comment) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO comments (good_id, username, content, time) " +
                "VALUES (?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, comment.getGoodId());
            statement.setString(2, comment.getUsername());
            statement.setString(3, comment.getContent());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            statement.setTimestamp(4, timestamp);
            statement.executeUpdate();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码10");
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
                JOptionPane.showMessageDialog(null, "错误码11");
                e.printStackTrace();
            }
        }
        return false;
    }

    public Comment[] getCommentList(int goodId) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM comments WHERE good_id=?";
        Comment[] commentList = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, goodId);
            resultSet = statement.executeQuery();
            int length = resultSet.getRow();

            for (int i = 0; i < length; i ++) {
                commentList[i].setNumber(resultSet.getInt("number"));
                commentList[i].setGoodId(resultSet.getInt("good_id"));
                commentList[i].setUsername(resultSet.getString("content"));
                commentList[i].setTimestamp(resultSet.getTimestamp("time"));

                resultSet.next();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码12");
            e.printStackTrace();
        }
        return commentList;
    }
}