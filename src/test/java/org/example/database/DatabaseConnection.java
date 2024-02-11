package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/tester";
            String username = "root";
            String password = "root123";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return connection;
    }


//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            String url = "jdbc:mysql://localhost:3306/tester";
//            String username = "root";
//            String password = "root123";
//            connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected to the database!");
//        } catch (SQLException e) {
//            System.err.println("Connection error: " + e.getMessage());
//        }
//        return connection;
//    }

}
