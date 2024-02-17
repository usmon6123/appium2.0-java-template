package org.example.phone3;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.example.constants.BaseConstants.TIME500;
import static org.example.constants.BaseConstants.my_page;

public class Helper extends BaseTest {


    public void click(By by) {
//        driver.manage().timeouts().implicitlyWait(TIME15, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click();
    }


    public boolean checkedByVisual(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(TIME500, MILLISECONDS);
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
        MobileElement element = (MobileElement) driver.findElement(by);
        element.sendKeys(txt);
    }

    public void sendPressKey(By by, String text) {
        findElement(by).click();
        driver.getKeyboard().pressKey(text);

    }

    public void goHome() {
        while (true) {
            boolean onHomePage = isElementDisplayed(my_page);
            if (!onHomePage) {
                // Ana sayfaya geri dönme işlemi
                driver.navigate().back();
                System.out.println("Ana sayfaya geri dönmek uzere");
            } else {
                System.out.println("Ana sayfaya geri dondu");
                break;
            }
        }
    }

    public static boolean isElementDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void bacKOne(By by) {
        driver.navigate().back();
    }


}
