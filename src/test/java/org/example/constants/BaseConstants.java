package org.example.constants;

import org.openqa.selenium.By;

public class BaseConstants {


    public static final String robot_name = "OGO001-1";
    public static final String finished = "finished";
    public static final String processing = "processing";
    public static final String waiting = "waiting";
    public static final String success = "Uğurla tamamlandı";
    public static final String not_bin = "BIN tapılmadı";
    public static final String fake_card = "Kart nömrəsi yanlışdır";
    public static final String failed = "Əməliyyat xətası";
    public static final String error = "hatalik var";


    public static final String PHONE1 = "93e3d5f00920";
    public static final Integer TIME6 = 2;
    public static final Integer TIME60 = 60;
    public static final Integer TIME500 =500;
    public static final Integer LIMIT_STATUS = 7;
    public static Integer statusE = 0;// 1 olursa error     0 olursa success;
    public static boolean successM = true;//default true ;


    public static final By defaultClickInHomePage = By.xpath("//android.view.View[@content-desc=\"Available balance \"]");
    public static final By amount = By.xpath("//android.view.View[contains(@content-desc,\"₼\")]");


    public static By findLocatorBySetText(String text) {
        return By.xpath("//android.widget.EditText[@text=\"" + text + "\"]");
    }

    public static By findByViewView(String text) {
        return By.xpath("//android.view.View[contains(@content-desc,\"" + text + "\")]");
    }



    //ILk parola sayfasi
    public static final By hello = By.xpath("//android.view.View[@content-desc=\"Hello \uD83D\uDC4B\uD83C\uDFFB\"]");
    public static final By my_qr = By.xpath("//android.view.View[@elementId = \"QR\"]");
    public static final By my_page = By.xpath("//android.widget.ImageView[@content-desc = \"Main page\"]");
    public static final By history = By.xpath("//android.widget.ImageView[@content-desc = \"History\"]");
    public static final By profile = By.xpath("//android.widget.ImageView[@content-desc = \"Profile\"]");
    public static final By save = By.xpath("//android.widget.ImageView[@content-desc = \"Save\"]");
    public static final By littleQR = By.xpath("//android.view.View[@content-desc=\"My QR\n" +
            "Show this code to get a transfer\"]/android.view.View[1]/android.widget.ImageView[1]");
}
