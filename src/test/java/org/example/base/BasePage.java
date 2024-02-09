package org.example.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage {

//    public AppiumDriver driver;
//    public WebDriverWait wait;
//
//    public BasePage(AppiumDriver<WebElement> driver){
//        System.out.println("BasePage");
//        Objects.requireNonNull(driver, "Driver cannot be null"); // Driver'ın null olup olmadığını kontrol ediyoruz
//
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver,60);
//    }

    AppiumDriver<WebElement> driver;
    WebDriverWait wait;

    public BasePage(AppiumDriver<WebElement> driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,60);
    }

    public WebElement findElement(By by){

        driver.manage().timeouts().implicitlyWait(100, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    public void sendKeys(By by, String txt){
        driver.manage().timeouts().implicitlyWait(100, SECONDS);
        findElement(by).clear();

        driver.manage().timeouts().implicitlyWait(100, SECONDS);
        findElement(by).sendKeys(txt);
    }

    public void click(By by){
        driver.manage().timeouts().implicitlyWait(100, SECONDS);
        findElement(by).click();
        driver.manage().timeouts().implicitlyWait(100, SECONDS);

    }
    public void clear(By by){
        findElement(by).clear();
    }

    public String getText(By by){
        return findElement(by).getText();
    }
}
