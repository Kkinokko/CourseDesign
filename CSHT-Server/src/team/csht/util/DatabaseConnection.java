package team.csht.util;

import java.sql.Connection;
import java.sql.DriverManager;

/** @author MnAs & Fe */
public class DatabaseConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/" +
            "csht?useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=Hongkong";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    private Connection connection = null;

    public DatabaseConnection() {
        try {
            Class.forName(DRIVER);
            System.out.println("数据库驱动程序加载成功");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("数据库连接成功");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}