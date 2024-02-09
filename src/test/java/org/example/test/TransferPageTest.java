package org.example.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.example.base.BaseTest;
import org.example.page.ProfilePage;
import org.example.page.TransferPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

public class TransferPageTest {


    public static AppiumDriver<WebElement> appiumDriver;
    public static WebDriverWait wait;

    TransferPage transferPage;

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

            appiumDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(appiumDriver, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test() throws InterruptedException {
        System.out.println("test");
        Thread.sleep(10000);
        for (int i = 0; i < 7; i++) {


            transferPage.transferClick();

            transferPage.transferToCardClick();

            transferPage.cardNumberClickAndSet();

            transferPage.balanceClickAndSet();

            transferPage.backBalancePage();

            transferPage.backCardPage();
        }
    }

    @AfterTest
    public void after() {
        System.out.println("after");
//        tearDown();
    }

}
