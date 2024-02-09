package org.example.page;

import io.appium.java_client.AppiumDriver;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Array;
import java.util.ArrayList;

import static org.example.constants.TransferConstants.*;

public class TransferPage extends BasePage {
    Integer count = 0;

    public TransferPage(AppiumDriver<WebElement> driver) {
        super(driver);
        System.out.println("TransferPage");
    }

    public TransferPage transferClick() {
        click(transfer);
        return this;
    }

    public TransferPage transferToCardClick() {
        click(transferToCard);
        return this;
    }

    public TransferPage cardNumberClickAndSet() {
        String[] cardNumber = {"5404085238660767", "5261633335130218", "5167513343864716", "5411249803402846", "5213675161956991", "5411249802477831", "5522099361737448", "5167513343377180", "5522099340561471"};
        int max = cardNumber.length, min = 0;
        sendKeys(cardNumberByTransfer, cardNumber[count < 8 ? count : 1]);
        count++;
        return this;
    }

    public TransferPage balanceClickAndSet() {
        sendKeys(balanceTransfer,String.valueOf(Math.floor(Math.random() * 100)));
        return this;
    }

    public TransferPage balanceIsSufficient(){
        findElement(balanceError);
        return this;
    }
    public TransferPage backBalancePage(){
        click(backBalance);
        return this;
    }

    public TransferPage backCardPage(){
        click(backCardNumber);
        return this;
    }


}
