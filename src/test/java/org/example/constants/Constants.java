package org.example.constants;

import org.openqa.selenium.By;

public class Constants {

    public static final By start = By.xpath("//android.view.View[@content-desc=\"0.00₼\"]");
    public static final By topUp = By.xpath("//android.widget.ImageView[@content-desc=\"Top up\"]");

    public static final By balanceText = By.xpath("//android.widget.EditText[@text=\"0 ₼\"]");
    public static final By continueButton = By.xpath("//android.view.View[@content-desc=\"Continue\"]");

    public static final By view = By.xpath("//android.webkit.WebView");

    public static final By scanCard = By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]");
    public static final By cardNumber = By.xpath("//android.widget.EditText[@resource-id=\"cp-pan-decor\"]");
    public static final By cardYear = By.xpath("//android.widget.EditText[@resource-id=\"cp-expiration-date\"]");

    public static final By cvv = By.xpath("//android.widget.EditText[@resource-id=\"cvv2\"]");

    public static final By register = By.xpath("//android.widget.CheckBox[@resource-id=\"registerCard\"]");
    //    --------------------
    public static final By backButton = By.xpath("//android.widget.ImageView");
    public static final By payButton = By.xpath("//android.widget.Button[@resource-id=\"OK\"]");
    public static final By cancelButton = By.xpath("//android.widget.Button[@text=\"Cancel\"]");
    //    -----errors------------------------
    public static final By cardNumberWrong = By.xpath("//android.view.View[@resource-id=\"pan-error\"]");
    public static final By cardYearWrong = By.xpath("//android.view.View[@resource-id=\"ExpYear-error\"]");
    public static final  By cvvError = By.xpath("//android.view.View[@resource-id=\"cvv2-error\"]");
}
