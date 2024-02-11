package org.example.m10;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryM10 {
    public OrderModul getTransaction() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();


        String sql = "select  * from orders where (orders.status = 'new') order by 'id' asc limit 1";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();


        OrderModul orderModul = null;
        while (resultSet.next()) {
            String sql2 = "update orders set status ='processing' where id =?";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            System.out.println("---------------" + resultSet.getInt("id") + "---------------");
            stmt2.setInt(1, resultSet.getInt("id"));
            stmt2.executeUpdate();

            Integer id = resultSet.getInt("id");
            Float price = resultSet.getFloat("price");
            String cardNumber = resultSet.getString("cardNumber");
            String cardYear = resultSet.getString("cardYear");
            String cvv2 = resultSet.getString("cvv2");
            String status = resultSet.getString("status");
            String message = resultSet.getString("message");

            orderModul = new OrderModul(id,price, cardNumber, cardYear, cvv2, status, message==null?"":message);
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return orderModul;
    }


    public boolean updateStatusSuccess(Integer id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String sql = "update orders set status ='success' where id =?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return true;
    }


    public boolean updateStatus(Integer id,String status, String message) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String sql = "update orders set status = '"+status+"', message = '"+message+"' where id =?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return true;
    }


    public boolean newData() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String sql = "select  * from orders where (orders.status = 'new') order by 'id' asc limit 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                return  true;
            }
            return false;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }


}
