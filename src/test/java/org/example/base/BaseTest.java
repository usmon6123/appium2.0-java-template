package org.example.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.net.URL;

public class BaseTest {
    public static AppiumDriver<WebElement> appiumDriver;
    public static WebDriverWait wait ;

    @BeforeClass
    public void setup(){
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

    public static AppiumDriver<WebElement> getAppiumDriver() {
        return appiumDriver;
    }

    public static void setAppiumDriver(AppiumDriver<WebElement> appiumDriver) {
        BaseTest.appiumDriver = appiumDriver;
    }


    public void  tearDown(){
        getAppiumDriver().quit();
    }
}

