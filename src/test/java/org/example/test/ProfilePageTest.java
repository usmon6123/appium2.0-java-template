package org.example.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.example.base.BaseTest;
import org.example.page.ProfilePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

public class ProfilePageTest extends BaseTest {

//    public ProfilePage profilePage;
//
//    @BeforeTest
//    public void before() {
//        System.out.println("before");
//        profilePage = new ProfilePage(getAppiumDriver());
//    }
//
//    @Test
//    public void test() {
//        System.out.println("test");
//    }
//
//    @AfterTest
//    public void after() {
//        System.out.println("after");
//        tearDown();
//    }

    ProfilePage profilePage;

    public static AppiumDriver<WebElement> appiumDriver;
    public static WebDriverWait wait ;
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
    public void test() {
        System.out.println("test");
    }

    @AfterTest
    public void after() {
        System.out.println("after");
//        tearDown();
    }

}
