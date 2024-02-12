package org.example.constants;

import org.openqa.selenium.By;

public class BaseConstant {
    public static final String robot_name = "OGO001-1";

    public static final By currentAmount = By.xpath("//android.view.View[@content-desc=\"0.00₼\"]");

    public static final By defaultClickInHomePage = By.xpath("//android.view.View[@content-desc=\"0.00₼\"]");

}
