package org.example.constants;

import org.openqa.selenium.By;

public class TopUpConstants {
    static final By start = By.xpath("//android.view.View[@content-desc=\"Start\"]");
    static final By topUp = By.xpath("//android.widget.ImageView[@content-desc=\"Top up\"]");
    static final By balanceText = By.xpath("//android.widget.EditText[@text=\"0 ₼\"]");
    static final By continueButton = By.xpath("//android.view.View[@content-desc=\"Continue\"]");
    static final By view = By.xpath("//android.webkit.WebView");
    static final By scanCard = By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]");
    static final By cardNumber = By.xpath("//android.widget.EditText[@resource-id=\"cp-pan-decor\"]");
    static final By cardYear = By.xpath("//android.widget.EditText[@resource-id=\"cp-expiration-date\"]");
    static final By cvv = By.xpath("//android.widget.EditText[@resource-id=\"cvv2\"]");
    static final By register = By.xpath("//android.widget.CheckBox[@resource-id=\"registerCard\"]");
}
