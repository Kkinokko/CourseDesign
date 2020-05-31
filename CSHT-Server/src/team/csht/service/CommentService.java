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
            return true;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码10");
            e.printStackTrace();
        }
        finally {
            try {
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
        String sql = "SELECT * FROM comments WHERE good_id=? ORDER BY time";
        Comment[] commentList = new Comment[100];

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, goodId);
            resultSet = statement.executeQuery();
            resultSet.next();

            for (int i = 0; ; i ++) {
                commentList[i] = new Comment();
                commentList[i].setNumber(resultSet.getInt(1));
                commentList[i].setGoodId(resultSet.getInt(2));
                commentList[i].setUsername(resultSet.getString(3));
                commentList[i].setContent(resultSet.getString(4));
                commentList[i].setTimestamp(resultSet.getTimestamp(5));

                if(!resultSet.next()) {
                    break;
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码12");
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
        return commentList;
    }
}