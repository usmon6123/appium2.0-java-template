package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            String database = "mrogo";
//            String server = "161.97.119.185";
//            String url = "jdbc:mysql://"+server+":3306/"+database;
//            String username = "mrogo";
//            String password = "ne2#_Uj2@ogo";
//            connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected to the database!");
//        } catch (SQLException e) {
//            System.err.println("Connection error: " + e.getMessage());
//        }
//        return connection;
//    }


    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/mrogo";
            String username = "root";
            String password = "root123";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return connection;
    }

}
