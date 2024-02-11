package org.example.m10;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.sql.SQLException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class test_m10 {


    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;

    By start = By.xpath("//android.view.View[@content-desc=\"Start\"]");
    By topUp = By.xpath("//android.widget.ImageView[@content-desc=\"Top up\"]");

    @BeforeTest
    public void beforeTest() {
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

            wait = new WebDriverWait(driver, 100000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    By balanceText = By.xpath("//android.widget.EditText[@text=\"0 ₼\"]");
    By continueButton = By.xpath("//android.view.View[@content-desc=\"Continue\"]");

    By view = By.xpath("//android.webkit.WebView");

    By scanCard = By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]");
    By cardNumber = By.xpath("//android.widget.EditText[@resource-id=\"cp-pan-decor\"]");
    By cardYear = By.xpath("//android.widget.EditText[@resource-id=\"cp-expiration-date\"]");

    By cvv = By.xpath("//android.widget.EditText[@resource-id=\"cvv2\"]");

    By register = By.xpath("//android.widget.CheckBox[@resource-id=\"registerCard\"]");

    @Test
    public void test() throws SQLException, InterruptedException {

        System.out.println("manuel olarak parolayi girit");
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        WebElement selectDevice = driver.findElement(topUp);
        selectDevice.click();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        System.out.println(driver.findElement(balanceText).getText());

        WebElement balanceTextE = driver.findElement(balanceText);
        balanceTextE.click();
        driver.getKeyboard().pressKey(String.valueOf(1500));
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        WebElement continueButtonE = driver.findElement(continueButton);
        continueButtonE.click();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        if (driver.findElement(view) != null) {
            WebElement viewE = driver.findElement(view);
            viewE.click();
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
        }

        WebElement cardNumberE = driver.findElement(cardNumber);
        cardNumberE.click();
        Thread.sleep(1000);
        if (driver.findElement(scanCard) != null) {
            cardNumberE.click();
        }

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        cardNumberE.click();

        MobileElement cardNumberE2 = (MobileElement) driver.findElement(cardNumber);
        cardNumberE2.sendKeys("1111222233334444");


        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        MobileElement cardYearE = (MobileElement) driver.findElement(cardYear);
        cardYearE.sendKeys("10/22");

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        MobileElement cvvE = (MobileElement) driver.findElement(cvv);
        cvvE.sendKeys("123");


        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        WebElement registerE = driver.findElement(register);
        registerE.click();
    }

    @AfterTest
    public void afterTest() {
//        driver.quit();
    }


//    ----------------------Helper methods-----------------------

    public void clickWebElement(By locator) {
        WebElement selectDevice = driver.findElement(locator);
        selectDevice.click();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
    }

}
