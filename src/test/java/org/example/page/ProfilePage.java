package org.example.page;

import io.appium.java_client.AppiumDriver;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;

import static org.example.constants.Constants.*;

public class ProfilePage extends BasePage {

//    AppiumDriver<WebElement> driver;

    public ProfilePage(AppiumDriver<WebElement> driver){

        super(driver);
        System.out.println("ProfilePage");
    }

    public ProfilePage profileClick(){
        click(topUp);
        return this;
    }
}
