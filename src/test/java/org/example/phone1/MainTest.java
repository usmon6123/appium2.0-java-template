package org.example.phone1;

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
        Thread.sleep(1000);
        helper.click(defaultClickInHomePage);
        Thread.sleep(500);
        while (true) {
            Thread.sleep(3000);

            //AdminPanel tablosundan robotun run column kiymetini getiriyor
            int robotRun = query.getRobotStatus();
            //TRANSFER
            if (robotRun == 2) {//robot run = 2 ise sadece transfer icin calisir robot

                while (statusE < LIMIT_STATUS && successM) {//islem ekstra hata verirse bir kac kere islemi tekrarlamak icin

                    statusE = getCurrentAmount();
                    System.out.println("current amount: " + currentAmount);
                    if (statusE < LIMIT_STATUS && statusE > 0) {
                        helper.goHome();//ana sayfaya geri dondurecek ordan yeniden transferi baslar
                        statusE = getCurrentAmount();
                    }
                    WithdrawalModule withdrawal = query.islemAl(200.0);//currentAmount
                    if (withdrawal == null) {
                        break;
                    }
                    statusE += transfer.transferMain(withdrawal);//islemi sonlandirsa 100 sonlandiramassa 1
                    if (statusE < LIMIT_STATUS && statusE > 0) {
                        helper.goHome();//ana sayfaya geri dondurecek ordan yeniden transferi baslar
                    }
                }
            }
        }
    }


    public int getCurrentAmount() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            String amountStr = driver.findElement(amount).getAttribute("content-desc");
            currentAmount = Double.valueOf(amountStr.substring(0, amountStr.length() - 1));
            successM = true;
            return 0;
        } catch (Exception e) {
            System.out.println("m10 bakiyesini alamdi");
            return 1;
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
