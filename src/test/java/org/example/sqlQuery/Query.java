package org.example.sqlQuery;

import org.example.database.DatabaseConnection;
import org.example.modul.WithdrawalModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.constants.BaseConstant.*;

public class Query {
    public int getRobotStatus() {
        Connection connection = DatabaseConnection.getConnection();
            int result = 0;
        try {
            String sql = "select a.run from adminpanel as a where a.robot_name = '" + robot_name + "' limit 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
               result = resultSet.getInt("run");
            }
        } catch (SQLException e) {
            System.out.println("sqlde hatalik olusdu, robot statusini bulamiyor");
            result = 0;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return result;
    }

    public WithdrawalModule islemAl(Double amount) {
        Connection connection = DatabaseConnection.getConnection();
        WithdrawalModule withdrawal = new WithdrawalModule();

        try {
            String sql = "UPDATE mrogo.withdrawals SET robot = '"+robot_name+"', robot_time = NOW() WHERE (robot IS NULL) AND amount<"+amount+" ORDER BY amount DESC Limit 1";
            PreparedStatement stmt = connection.prepareStatement(sql);

            String sql2 = "SELECT id, transaction_id, amount, card_no, expiry_date, robot FROM mrogo.withdrawals WHERE robot = '"+robot_name+"' AND robot_status IS NULL limit 1";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            ResultSet resultSet = stmt2.executeQuery();

            while (resultSet.next()) {
                withdrawal.setId(resultSet.getInt(1));
                withdrawal.setTransaction_id(resultSet.getString(2));
                withdrawal.setAmount(resultSet.getDouble(3));
                withdrawal.setCard_no(resultSet.getString(4));
                withdrawal.setExpiry_date(resultSet.getString(5));
                withdrawal.setRobot(resultSet.getString(6));
            }

        }catch (Exception e){
            e.printStackTrace();
            return new WithdrawalModule();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return withdrawal ;
    }















//     public Double getCurrentAmount() {
//        Connection connection = DatabaseConnection.getConnection();
//        double result = 0.0;
//        try {
//            String sql = "";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            ResultSet resultSet = stmt.executeQuery();
//
//            while (resultSet.next()) {
//                result = resultSet.getInt(3);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return 0.0;
//        }finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    System.err.println("Error closing connection: " + e.getMessage());
//                }
//            }
//        }
//        return result;
//    }
}
