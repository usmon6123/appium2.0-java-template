package org.example.phone3;

import org.example.database.DatabaseConnection;
import org.example.modul.WithdrawalModule;
import org.example.sqlQuery.Query;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.example.constants.BaseConstants.*;

public class MainTest extends BaseTest {
    private static final Transfer transfer = new Transfer();
    private static final Query query = new Query();
    private static final Helper helper = new Helper();
    public static Double currentAmount = 0.0;
    public static WebElement currentWebElement = null;

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(5000);
        helper.click(defaultClickInHomePage);
        Thread.sleep(500);
        while (true) {
            Thread.sleep(3000);

            //AdminPanel tablosundan robotun run column kiymetini getiriyor
            int robotRun = query.getRobotStatus();
            //TRANSFER
            if (robotRun == 2) {//robot run = 2 ise sadece transfer icin calisir robot

                while (true) {
                    try {
                        getCurrentAmount();
                        System.out.println("current amount: " + currentAmount);
                        WithdrawalModule withdrawal = query.islemAl(200.0);//currentAmount
                        if (withdrawal == null) {
                            break;
                        }
                        transfer.transferMain(withdrawal);//islemi sonlandirsa 100 sonlandiramassa 1
                        break;
                    } catch (Exception e) {
                        helper.goHome();//ana sayfaya geri dondurecek ordan yeniden transferi baslar
                    }
                }
            }
        }
    }


    public void getCurrentAmount() {
        Connection connection = DatabaseConnection.getConnection();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String amountStr = driver.findElement(amount).getAttribute("content-desc");
        currentAmount = Double.valueOf(amountStr.substring(0, amountStr.length() - 1));
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
