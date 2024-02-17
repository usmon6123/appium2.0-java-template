package org.example.phone2;

import org.example.phone1.BaseTest;
import org.example.phone1.Transfer;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.example.constants.BaseConstants.*;

public class MainTest extends BaseTest {
    private static final Transfer transfer = new Transfer();
    private static final Helper helper = new Helper();


    @Test
    public void test() throws InterruptedException {
        Thread.sleep(10000);
        int count = 0;
        while (true) {

            if (isElementDisplayed(my_page)){
//                if (isElementDisplayed(save)){
//                    driver.manage().timeouts().implicitlyWait(3,SECONDS);
//                    driver.findElement(littleQR).click();
//                }
                Thread.sleep(5000);
                System.out.println("ana sayfadasiniz");
            }

            try {
                driver.findElement(my_page).click();
            } catch (Exception e) {
                goHome();
            }
            if(!isElementDisplayed(my_page)){
                goHome();
            }
        }
    }

    private void goHome() {
        int count = 0;
        while (count < 10) {
            boolean onHomePage = isElementDisplayed(my_page);
            if (!onHomePage) {
                // Ana sayfaya geri dönme işlemi
                driver.navigate().back();
                System.out.println("Ana sayfaya geri dönmek uzere");
                count++;
            } else {
//                driver.manage().timeouts().implicitlyWait(5,SECONDS);
//                driver.findElement(my_page).click();
                System.out.println("Ana sayfaya geri dondu");
                count = 12;
            }
        }
    }

    private static boolean isElementDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}






//            Thread.sleep(5000);
//            try {
//                if(driver.findElement())
//            }catch (Exception e){
//                System.out.println("proekt portladi");
//            }





    //        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.findElement(defaultClickInHomePage);
//    List<WebElement> viewElements = driver.findElements(By.xpath("//android.view.View"));
//    List<WebElement> editTextElements = driver.findElements(By.xpath("//android.widget.EditText"));
//    List<WebElement> frameLayoutElements = driver.findElements(By.xpath("//android.widget.EditText"));






//  driver.manage().timeouts().implicitlyWait(15,SECONDS);
//            if (driver.findElement(transferToCard) != null) {
//                helper.click(transferToCard);}
//            if (driver.findElement(HISTORY).isDisplayed())
//                helper.click(HISTORY);
//            if (driver.findElement(PROFILE).isDisplayed())
//                helper.click(PROFILE);
//            if (driver.findElement(MY_PAGE).isDisplayed())
//                helper.click(MY_PAGE);