package org.example.phone2;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.net.URL;

import static org.example.constants.BaseConstants.PHONE1;
import static org.example.constants.BaseConstants.TIME15;

public class BaseTest {
    public static AppiumDriver<WebElement> driver;
    public static WebDriverWait wait;


    @BeforeClass
    public void setup() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");
            cap.setCapability("udid", PHONE1);


            cap.setCapability("appPackage", "com.m10");
            cap.setCapability("appActivity", ".MainActivity");

            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");

            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, TIME15);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() {
//        driver.quit();
    }


}




