package org.example.constants;

import org.openqa.selenium.By;

public class TransferConstants {

    public static final By transfer = By.xpath("//android.widget.ImageView[@content-desc=\"Transfer\"]");

    public static final By transferToCard = By.xpath("//android.widget.ImageView[@content-desc=\"Transfer to card\"]");

    public static final By cardNumberBar = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[2]");

    public static final By cardNumberByTransfer = By.xpath("//android.widget.EditText");

    public static final By continueTransfer = By.xpath("//android.view.View[@content-desc=\"Continue\"]");

    public static final By invalidCardNumber = By.xpath("//android.view.View[@content-desc=\"Invalid card number\"]");

    public static final By balanceTransfer = By.xpath("//android.widget.EditText[@text=\"0 ₼\"]");

    public static final By balanceError = By.xpath("//android.view.View[@content-desc=\"Insufficient funds. Balance 0.00 ₼\"]");

    public static final By balancePage = By.xpath("//android.view.View[starts-with(@content-desc, \"Balance:\")]");

    public static final By ExpDate = By.xpath("//android.widget.EditText");

    public static final By ExpDatePage = By.xpath("//android.view.View[@content-desc=\"To transfer to the card, enter the validity period of this card\"]");


    public static final By transferBalance = By.xpath("//android.view.View[@content-desc=\"Transfer\"]");

    public static final By backBalance = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[1]");

    public static final By backCardNumber = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView");
}
