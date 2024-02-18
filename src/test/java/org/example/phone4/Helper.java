package org.example.phone4;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.example.constants.BaseConstants.*;
import static org.example.constants.SwingConstants.homePage;
import static org.example.constants.SwingConstants.takeWithdraw;
import static org.example.constants.TransferConstants.*;

public class Helper extends BaseTest {


    public void click(By by) {
        waitIfPausedTrue();
        driver.manage().timeouts().implicitlyWait(TIME6, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click();
    }


    public boolean checkedByVisual(By by) {
        try {
            waitIfPausedTrue();//pause tusu tiklandiginda projeni durdurmak icin
            driver.manage().timeouts().implicitlyWait(TIME6, SECONDS);
            WebElement element = driver.findElement(by);
            System.out.println(element.toString() != null ? element.toString() : "null");
            return element != null;
        } catch (Exception e) {
            System.out.println("ui gozukmedi    ->   " + by.toString());
            return false;
        }
    }

    public WebElement findElement(By by) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return driver.findElement(by);
        } catch (Exception e) {
            return null;
        }
    }

    public void sendMobileKeys(By by, String txt) {
        waitIfPausedTrue();
        MobileElement element = (MobileElement) driver.findElement(by);
        element.sendKeys(txt);
    }

    public void sendPressKey(By by, String text) {
        waitIfPausedTrue();
        findElement(by).click();
        driver.getKeyboard().pressKey(text);
    }

    public void goHome() {
        while (true) {
            waitIfPausedTrue();

            boolean onHomePage = isElementDisplayed(my_page);
            if (!onHomePage) {
                // Ana sayfaya geri dönme işlemi
                driver.navigate().back();
                System.out.println("Ana sayfaya geri dönmek uzere");
            } else {
                click(my_page);
                updatePopUp(takeWithdraw,homePage);
                waitIfPausedTrue();
                break;
            }

        }
    }

    public boolean isElementDisplayed(By by) {
        waitIfPausedTrue();//pause tusu tiklandiginda projeni durdurmak icin

        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public static void bacKOne(By by) {
        driver.navigate().back();
    }

    public void swipe() {
        TouchAction swipeAction = new TouchAction(driver);
        swipeAction
                .press(PointOption.point(557, 410))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(557, 570))
                .release()
                .perform();
    }


    //ISLEM UGURLA TAMAMLANSA 1, EMALIYAT HATASI ISE 0 , MANUEL BAKMAK GEREKIRSE 2
    public int controlTransfer(String cardNumber, String amount) {
        while (true) {
            waitIfPausedTrue();//pause tusu tiklandiginda projeni durdurmak icin

            try {
                click(history);
                //cardNumber ve amount ayni olan historydegi elementleri listeye topliyoruz
                List<WebElement> elements = driver.findElements(historyListPath(cardNumber, amount));
                int success = 0;
                int error = 0;

                for (WebElement element : elements) {
                    if (element.getAttribute("content-desc").contains(operationErrorMessage)) error++;
                    else if (element.getAttribute("content-desc").contains(transferToCardMessage)) success++;
                }
                goHome();
                if (error == elements.size() && success == 0) return 0;
                else if (success == elements.size() && error == 0) return 1;
                return 2;
            }catch (Exception e){
                goHome();
            }
        }
    }

    public static boolean controlTransferSimple(String cardNumber, String amount) {
        List<WebElement> elements = driver.findElements(historyListPath(cardNumber, amount));
        return elements == null;
    }

    public static boolean frameYesNo(String message) {

        int option = JOptionPane.showOptionDialog(null, message, robot_name, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Yes", "No"}, "Yes");

        // Kullanıcının seçimine göre işlem yap
        if (option == JOptionPane.YES_OPTION) {
            return true;
        } else if (option == JOptionPane.NO_OPTION) {
            return false;
        } else {
            return frameYesNo(message);
        }
    }
}
