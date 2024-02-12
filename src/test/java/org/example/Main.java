package org.example;

import org.example.base.BaseTest;
import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
        String code = "4994998888".substring(0,6);
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select b.BINCode  from bincode b where b.CaseNo = 0 limit 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String str = resultSet.getString("BINCode");
            System.out.println(checked(code, str));
        }

//        String sql2 = "SELECT COUNT(*) AS count FROM bincode WHERE ? IN (BINCode) AND CaseNo = 0";
//        PreparedStatement statement2 = connection.prepareStatement(sql2);
//        statement2.setString(1, code);
//        ResultSet resultSet2 = statement2.executeQuery();
//        if (resultSet2.next()) {
//            int count = resultSet2.getInt("count");
//            System.out.println("================================================================");
//            System.out.println(count > 0);
//            System.out.println("================================================================");
//        }

    }

    public static boolean checked(String code, String str) {

        String[] codes = str.split(","); // Virgül ile ayır
//        System.out.println(Arrays.toString(codes));
        for (String c : codes) {
            if (c.trim().equals(code)) { // Boşlukları kaldırarak kodları karşılaştır
                return true; // Kod bulunduğunda true döndürür
            }
        }
        return false; // Kod bulunamadığında false döndürür
    }
}
