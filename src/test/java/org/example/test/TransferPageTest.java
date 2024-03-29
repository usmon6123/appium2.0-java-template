package org.example.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.example.modul.WithdrawalModule;
import org.example.sqlQuery.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.example.constants.BaseConstant.*;
import static org.example.constants.TransferConstants.*;

public class TransferPageTest {


    public static AppiumDriver<WebElement> driver;
    public static WebDriverWait wait;
    static Integer n = 0;

//    TransferPage transferPage = new TransferPage(appiumDriver);

    @BeforeTest
    public void before() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");
            cap.setCapability("udid", "93e3d5f00920");

            cap.setCapability("appPackage", "com.m10");
            cap.setCapability("appActivity", ".MainActivity");

            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");

            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, 15);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test() throws InterruptedException {

        Query query = new Query();
        while (true) {
            click(currentAmount);
            Thread.sleep(1000);
            int status = query.getRobotStatus();
            //TRANSFER
            if (status == 2) {

//                double amount = getCurrentAmount(currentAmount, query);
//                if (amount == 0.0) {
//                    continue;
//                }

                WithdrawalModule withdrawal = query.islemAl(100.0);//amount
                if (withdrawal == null) {
                    continue;
                }

                //kard girisde hatalik varsa,ise tamamlayacak ve comment = "Kart nömrəsi yanlışdır" diyecek
                if (!kardGiris(withdrawal)) {
                    //sqle giderek isi tamamliyor ve comment = "Kart nömrəsi yanlışdır" ve result = 0 olarak guncelliyor;
                    query.updateStatusResultCommentById(FINISHED, 0, FAKE_CARD, 0.0, withdrawal.getId());
                    System.out.println("Kard bilgilerini giremedi");
                    continue;
                }


                if (!tutarGiris(withdrawal)) {
                    System.out.println("Tutar giremedi");
                    //sqle giderek isi baskasi almasi icin robotun bilgilerini siliyor
                    query.updateRobotNull(withdrawal.getId());
                    continue;
                }

                //burasini guncellemek gerek
                if (!result(withdrawal)) {
                    query.updateStatusResultCommentById(FINISHED, 0, FAILED, 0.0, withdrawal.getId());
                }
                query.updateStatusResultCommentById(FINISHED, 1, SUCCESS, withdrawal.getAmount(), withdrawal.getId());
            }
        }
    }

    private boolean result(WithdrawalModule withdrawal) {
        try {
            if (checked(continueTransfer)) {
                click(continueTransfer);
                return true;
            }
            ;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean tutarGiris(WithdrawalModule withdrawal) {
        try {
            click(balanceTransfer);
            sendBalance(balanceTransfer, withdrawal.getAmount());
            if (isEnabled(balanceError)) {
                if (n == 1) {
                    click(backBalance);
                    n = 0;//hatalik olursa geri donmek icin adim sanamaya gerek
                }
                click(backBalance);
                click(backCardNumber);
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean kardGiris(WithdrawalModule withdrawal) {
        try {
            click(findLocatorByText("Transfer"));

////android.widget.ImageView[@content-desc="Transfer to card"]

            click(transfer);
            click(transferToCard);
            click(cardNumberBar);
            click(cardNumberByTransfer);
            String cardNum = withdrawal.getCard_no();

            if (cardNum.length() != 16) {
                click(backCardNumber);
                return false;
            }
            sendMobileKeys(cardNumberByTransfer, cardNum);
            click(continueTransfer);
            if (checkedByVisual(invalidCardNumber)) {
                if (checkedByVisual(continueTransfer)) {
                    click(backCardNumber);
                    return false;
                }
            }
//            if (checkedByVisual(ExpDate)) {
//                n = 1;//hatalik olursa geri donmek icin adim sanamaya gerek
//                click(ExpDate);
//                sendKeys(ExpDate, withdrawal.getExpiry_date());
//                click(continueTransfer);
//
//                if (checkedByVisual(continueTransfer)) {
//                    click(backBalance);
//                    if (checkedByVisual(continueTransfer)) {
//                        click(backCardNumber);
//                    }
//                    return false;
//                }

                if (!checked(balanceTransfer)) {
                    click(backBalance);
                    if(checked(continueTransfer)){
                        click(backCardNumber);
                    }
                    return false;
                }
//            }
//            if (checkedByVisual(continueTransfer)) {
//                click(backCardNumber);
//                return false;
//            }
            if (checkedByVisual(qr)) {
                click(backCardNumber);
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @AfterTest
    public void after() {
        System.out.println("after");
//        tearDown();
    }


    public WebElement findElementById(By by) {

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    public boolean checked(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(1, SECONDS);
            return driver.findElement(by) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkedByVisual(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(1, SECONDS);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            System.out.println(element.toString() != null ? element.toString() : "null");
            return element != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Double getCurrentAmount(By by, Query query) {
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
        String str = driver.findElement(by).getAttribute("content-desc");
        click(currentAmount);
        System.out.println(str.length() - 2);
        return Double.valueOf(str.substring(0, str.length() - 2));
    }

    public WebElement findElement(By by) {
//        driver.manage().timeouts().implicitlyWait(15, SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    //yazma alani var olan hujreler icin
    public static By findLocatorByText(String text) {
        return By.xpath("//android.widget.EditText[@text=\"" + text + "\"]");
    }

    //resimli buttonler icin
    public static By findLocatorByContent(String text) {
        return By.xpath("//android.widget.ImageView[@content-desc=\""+text+"\"]");
    }

    public void sendKeys(By by, String txt) {

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        findElement(by).sendKeys(txt);
    }

    public boolean isEnabled(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(5, SECONDS);
            return wait.until(ExpectedConditions.presenceOfElementLocated(by)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }


    public void sendBalance(By by, Double balance) {

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        findElement(by).click();
        driver.getKeyboard().pressKey(String.valueOf(balance));

    }

    public void sendMobileKeys(By by, String txt) {
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        MobileElement cardNumberE2 = (MobileElement) driver.findElement(by);
        cardNumberE2.sendKeys(txt);

    }

    public void click(By by) {
//        driver.manage().timeouts().implicitlyWait(15, SECONDS);
        findElement(by).click();
    }

    public void clear(By by) {
        findElement(by).clear();
    }

    public String getText(By by) {
        return findElement(by).getText();
    }


}
