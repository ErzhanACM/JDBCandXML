package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionToDB {

    public static Connection getConnection() throws SQLException {

        Connection con = null;

        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

        String url = resourceBundle.getString("url");
        String driver = resourceBundle.getString("driver");
        String user = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
