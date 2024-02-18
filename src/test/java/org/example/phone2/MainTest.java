package org.example.phone2;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.example.constants.TransferConstants;
import org.example.phone1.BaseTest;
import org.example.phone1.Transfer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.swing.JOptionPane.showOptionDialog;
import static org.example.constants.BaseConstants.*;
import static org.example.constants.TransferConstants.*;

public class MainTest extends BaseTest {
    private static final Transfer transfer = new Transfer();
    private static final Helper helper = new Helper();


    @Test
    public void test() throws InterruptedException {
        Thread.sleep(10000);
        int c = 0;
        while (c < 5) {
            try {
                if (isElementDisplayed(TransferConstants.transfer)) {
                    helper.click(history);
                    String card = "4127 20.. .... 0657";
                    String amount = "1.00";
                    if (isElementDisplayed(historyListPath(card, amount))) {
                        System.out.println(frameYesNo("test message"));
                        System.out.println("============================");
                        if (controlTransfer(card, amount)) {
                            System.out.println("transfer success");
                        } else {
                            System.out.println("transfer failure");
                        }
                    }

                } else {
                    goHome();
                }
            } catch (Exception e) {
                goHome();
            }
            c++;
        }
    }


    public boolean controlTransfer(String cardNumber, String amount) {
        List<WebElement> elements = driver.findElements(historyListPath(cardNumber, amount));
        int count = 0;
        ArrayList<String> contents = new ArrayList<>();
        //cardNumber ve amount ayni olan historydegi elementleri listeye topliyoruz
        for (WebElement element : elements) {
            String attribute = element.getAttribute("content-desc");
            if (attribute.contains(operationErrorMessage)) count++;
            contents.add(attribute);
        }
        if (count == 0) return false; //"operationErrorMessage" alan biran tane element bulunmassa false
        return (count == contents.size());//"operationErrorMessage" alan elementler sani liste uzunligi ile berabar olsa true yoksa false

    }

    public static boolean frameYesNo(String message) {

        int option = JOptionPane.showOptionDialog(null, message, robot_name, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Yes", "No"}, "Yes");

        // Kullanıcının seçimine göre işlem yap
        if (option == JOptionPane.YES_OPTION) {
            return true;
        } else if (option == JOptionPane.NO_OPTION) {
            return false;
        } else {
            return frameYesNo(message);
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
                helper.click(my_page);
                System.out.println("Ana sayfaya geri dondu");
                count = 12;
            }
        }
    }

    private static boolean isElementDisplayed(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(5, SECONDS);
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}








//    public void test() throws InterruptedException {
//        Thread.sleep(10000);
//        int count = 0;
//        while (true) {
//
//            if (isElementDisplayed(my_page)){
////                if (isElementDisplayed(save)){
////                    driver.manage().timeouts().implicitlyWait(3,SECONDS);
////                    driver.findElement(littleQR).click();
////                }
//                Thread.sleep(5000);
//                System.out.println("ana sayfadasiniz");
//            }
//
//            try {
//                driver.findElement(my_page).click();
//            } catch (Exception e) {
//                goHome();
//            }
//            if(!isElementDisplayed(my_page)){
//                goHome();
//            }
//        }
//    }




















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














//    public static void speak(String text) {
//        Voice voice;
//        VoiceManager voiceManager = VoiceManager.getInstance();
//        voice = voiceManager.getVoice("kevin16");
//        voice.allocate();
//        voice.speak(text);
//    }