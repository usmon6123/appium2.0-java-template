package org.example.phone1;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.example.constants.BaseConstants.TIME500;

public class Helper extends BaseTest {


    public void click(By by) {
//        driver.manage().timeouts().implicitlyWait(TIME15, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click();
    }


    public boolean checkedByVisual(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(TIME500, MILLISECONDS);
            WebElement element =driver.findElement(by);
            System.out.println(element.toString() != null ? element.toString() : "null");
            return element != null;
        } catch (Exception e) {
            System.out.println("ui elementi bulamadi");
            return false;
        }
    }

    public WebElement findElement(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    public void sendMobileKeys(By by, String txt) {
        MobileElement element = (MobileElement) driver.findElement(by);
        element.sendKeys(txt);
    }

        public void sendPressKey(By by, String text) {
        findElement(by).click();
        driver.getKeyboard().pressKey(text);

    }

}
