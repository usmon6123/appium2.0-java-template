package org.example.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.example.constants.TransferConstants.*;

public class TransferPageTest {


    public static AppiumDriver<WebElement> driver;
    public static WebDriverWait wait;

//    TransferPage transferPage = new TransferPage(appiumDriver);

    @BeforeTest
    public void before() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");
            cap.setCapability("udid", "93e3d5f00920");

            cap.setCapability("appPackage", "com.m10");
            cap.setCapability("appActivity", ".MainActivity");

            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");

            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test() {

       click(transfer);
       click(transferToCard);
       click(cardNumberBar);
       click(cardNumberByTransfer);


        sendMobileKeys(cardNumberByTransfer,"5167513343864716");
       click(continueTransfer);




    }

    @AfterTest
    public void after() {
        System.out.println("after");
//        tearDown();
    }









    public WebElement findElementById(By by){

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }



    public WebElement findElement(By by){

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    public void sendKeys(By by, String txt){

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        findElement(by).sendKeys(txt);
    }

    public void sendMobileKeys(By by, String txt){
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        MobileElement cardNumberE2 = (MobileElement) driver.findElement(by);
        cardNumberE2.sendKeys(txt);



    }

    public void click(By by){
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        findElement(by).click();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

    }
    public void clear(By by){
        findElement(by).clear();
    }

    public String getText(By by){
        return findElement(by).getText();
    }


}
