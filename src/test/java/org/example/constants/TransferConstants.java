package org.example.constants;

import org.openqa.selenium.By;

public class TransferConstants {

    public static final By transfer = By.xpath("//android.widget.ImageView[@content-desc=\"Transfer\"]");

    public static final By transferToCard = By.xpath("//android.widget.ImageView[@content-desc=\"Transfer to card\"]");

//    //card girmeden once perde var onu kaldirmak icin bu "bar" xpathi tiklamak gerekiyor 1 defa
//    public static final By bar = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View");

    public static final By cardNumberByTransfer = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[2]");

    public static final By continueTransfer = By.xpath("//android.view.View[@content-desc=\"Continue\"]");

    public static final By invalidCardNumber = By.xpath("//android.view.View[@content-desc=\"Invalid card number\"]");

    public static final By balanceTransfer = By.xpath("//android.widget.EditText[@text=\"0 ₼\"]");

    public static final By balanceError = By.xpath("//android.view.View[@content-desc=\"Insufficient funds. Balance 0.00 ₼\"]");

    public static final By transferBalance = By.xpath("//android.view.View[@content-desc=\"Transfer\"]");

    public static  final By backBalance = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[1]");

    public static  final By backCardNumber = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView");
}
