package org.example.constants;

import org.openqa.selenium.By;

public class BaseConstant {
    public static final String robot_name = "OGO001-1";
    public static final String FINISHED = "finished";
    public static final String PROCESSING = "processing";
    public static final String WAITING = "waiting";
    public static final String SUCCESS = "Uğurla tamamlandı";
    public static final String NOT_BIN = "BIN tapılmadı";
    public static final String FAKE_CARD = "Kart nömrəsi yanlışdır";
    public static final String FAILED = "Əməliyyat xətası";
    public static final String ERROR = "hatalik var";



    public static final By currentAmount = By.xpath("//android.view.View[@content-desc=\"0.00₼\"]");

    public static final By defaultClickInHomePage = By.xpath("//android.view.View[@content-desc=\"0.00₼\"]");
    public static final By qr = By.xpath("//android.widget.ImageView[@content-desc=\"Save\"]");

}
