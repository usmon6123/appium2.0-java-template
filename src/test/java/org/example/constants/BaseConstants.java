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
    public static final Integer TIME500 = 500;



    public static final By defaultClickInHomePage = By.xpath("//android.view.View[@content-desc=\"0.00₼\"]");
    public static final By amount = By.xpath("//android.widget.TextView[contains(@content-desc, \"₼\")]");
    public static final String TRANSFER = "Transfer";


    public static By findLocatorBySetText(String text) {
        return By.xpath("//android.widget.EditText[@text=\"" + text + "\"]");
    }

    //resimli buttonler icin
    public static By findLocatorByViewText(String text) {
        return By.xpath("//android.widget.ImageView[@content-desc=\""+text+"\"]");
    }


}
