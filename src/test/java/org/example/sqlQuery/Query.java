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
            String sql = "UPDATE mrogo.withdrawals SET robot = ?, robot_time = NOW() WHERE (robot is null ) AND amount < ? ORDER BY amount DESC Limit 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, robot_name);
            stmt.setDouble(2, amount);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) return null;

            //comment -> robot_status ->
            String sql2 = "SELECT id, transaction_id, amount, card_no, expiry_date, robot FROM mrogo.withdrawals WHERE robot = ?  AND robot_status is null limit 1";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setString(1, robot_name);
            ResultSet resultSet = stmt2.executeQuery();

            while (resultSet.next()) {
                withdrawal.setId(resultSet.getInt(1));
                withdrawal.setTransaction_id(resultSet.getString(2));
                withdrawal.setAmount(resultSet.getDouble(3));
                withdrawal.setCard_no(resultSet.getString(4));
                withdrawal.setExpiry_date(resultSet.getString(5));
                withdrawal.setRobot(resultSet.getString(6));
            }

            //BIN Blocked ve Bankayi nezarat edecek yerin kodu yazilmadi






            String str3 = "UPDATE mrogo.withdrawals SET robot_status = ? WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(str3);
            statement.setString(1, PROCESSING);
            statement.setDouble(2, withdrawal.getId());


        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return withdrawal;
    }

    public boolean updateStatusResultCommentById(String apiStatus, int result, String comment, Double amountReceived, Integer withDrawingId) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String sql = "UPDATE mrogo.withdrawals SET api_status = ?, result = ?, comment = ?, finish_time = NOW(), amount_received = ? WHERE (id = ?);";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, apiStatus);
            stmt.setInt(2, result);
            stmt.setString(3, comment);
            stmt.setDouble(4, amountReceived);
            stmt.setInt(5, withDrawingId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) return false;

        } catch (Exception e) {
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


    public boolean updateRobotNull(Integer withdrawalId) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String sql = "UPDATE mrogo.withdrawals SET robot = NULL, robot_time = NULL, robot_status = NULL  WHERE (id = ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, withdrawalId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return true;
    }
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

