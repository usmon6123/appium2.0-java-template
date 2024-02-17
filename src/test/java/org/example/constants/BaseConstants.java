package org.example.constants;

import org.openqa.selenium.By;

public class BaseConstants {


    public static final String robot_name = "OGO001-1";
    public static final String FINISHED = "finished";
    public static final String PROCESSING = "processing";
    public static final String WAITING = "waiting";
    public static final String SUCCESS = "Uğurla tamamlandı";
    public static final String NOT_BIN = "BIN tapılmadı";
    public static final String FAKE_CARD = "Kart nömrəsi yanlışdır";
    public static final String FAILED = "Əməliyyat xətası";
    public static final String ERROR = "hatalik var";


    public static final String PHONE1 = "93e3d5f00920";
    public static final Integer TIME15 = 15;
    public static final Integer TIME60 = 60;
    public static final Integer TIME500 =200;
    public static final Integer LIMIT_STATUS = 7;
    public static Integer statusE = 1;//default 1 olmasinin sebebbi methodlar success tamamlanirsa  status = 0 gonderejek;
    public static boolean successM = true;//default true ;


    public static final By defaultClickInHomePage = By.xpath("//android.view.View[@content-desc=\"Available balance \"]");
    public static final By amount = By.xpath("//android.view.View[contains(@content-desc,\"₼\")]");


    public static By findLocatorBySetText(String text) {
        return By.xpath("//android.widget.EditText[@text=\"" + text + "\"]");
    }

    //resimli buttonler icin
    public static By findLocatorByViewText(String text) {
        return By.xpath("//android.widget.ImageView[@content-desc=\"" + text + "\"]");
    }

    //ILk parola sayfasi
    public static final By Hello = By.xpath("//android.view.View[@content-desc=\"Hello \uD83D\uDC4B\uD83C\uDFFB\"]");
    public static final By MY_QR = By.xpath("//android.view.View[@elementId = \"QR\"]");
    public static final By my_page = By.xpath("//android.widget.ImageView[@content-desc = \"Main page\"]");
    public static final By HISTORY = By.xpath("//android.widget.ImageView[@content-desc = \"History\"]");
    public static final By PROFILE = By.xpath("//android.widget.ImageView[@content-desc = \"Profile\"]");
    public static final By save = By.xpath("//android.widget.ImageView[@content-desc = \"Save\"]");
    public static final By littleQR = By.xpath("//android.view.View[@content-desc=\"My QR\n" +
            "Show this code to get a transfer\"]/android.view.View[1]/android.widget.ImageView[1]");
}
