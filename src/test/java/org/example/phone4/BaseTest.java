package org.example.phone4;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static org.example.constants.BaseConstants.*;
import static org.example.constants.SwingConstants.*;

public class BaseTest {
    public static AppiumDriver<WebElement> driver;
    public static WebDriverWait wait;


    @BeforeClass
    public void setup() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");
            cap.setCapability("udid", PHONE1);


            cap.setCapability("appPackage", "com.m10");
            cap.setCapability("appActivity", ".MainActivity");

            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");

            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, TIME6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        createPopUp();
        updatePopUp("Islem Al","Ana sayfa");
    }




    @AfterTest
    public void tearDown() {
//        driver.quit();
    }



    public static void createPopUp() {
        frame = new JFrame(robot_name);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 5, 2, 2); // Labeller arasındaki boşluğu ayarla
        gbc.anchor = GridBagConstraints.NORTHWEST;

        subflowL = new JLabel("Subflow: ");
        pageL = new JLabel("Page: ");
        actionL = new JLabel("Action: ");

        subflowValue = new JLabel("subflow value");
        pageValue = new JLabel("page value");
        actionValue = new JLabel("");

        processL.setForeground(new Color(0, 0, 255)); // label1'in yazı rengini mavi (RGB: 0, 0, 255) olarak ayarla

        JPanel headerPanel = doubleLabelPanel(processL, robotL);
        JPanel subflowPanel = doubleLabelPanel(subflowL,subflowValue);
        JPanel pagePanel = doubleLabelPanel(pageL,pageValue);
        JPanel actionPanel = doubleLabelPanel(actionL,actionValue);
        JPanel panel2 = buttonsPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(headerPanel,gbc);
        gbc.gridy++;
        panel.add(subflowPanel,gbc);
        gbc.gridy++;
        panel.add(pagePanel,gbc);


        frame.add(panel);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setSize(330, 220);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }

    public static JPanel doubleLabelPanel(JLabel label1, JLabel label2){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label1.setFont(new Font("Arial", Font.PLAIN, 14));
        label2.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label1);
        panel.add(label2);
        return panel;
    }

    private static JPanel buttonsPanel() {

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel2.setBackground(Color.decode("#c9d6fb"));

        // Butonu oluştur
        JButton button = new JButton("Pause");

        // Butonun hizalamasını ayarla
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Butona ActionListener ekle
        button.addActionListener(e -> {
            if (paused) {
                paused = false;
                button.setText("Pause");
            } else {
                paused = true;
                button.setText("Resume");
            }
        });

        // Butonu altta ve merkezde ekle
        panel2.add(button);
//        panel2.add(homeButton);

        return panel2;
    }

    public static void updatePopUp(String subflow, String page) {
        if (!frame.isVisible()) { // Eğer popup penceresi kapalıysa
            createPopUp(); // Popup penceresini yeniden oluştur
        }
        // Etiketleri güncelle
        processL.setText("Running");
        subflowValue.setText(subflow);
        pageValue.setText(page);
    }

    public static void updatePopUp(String subflow, String page, String action) {
        if (!frame.isVisible()) { // Eğer popup penceresi kapalıysa
            createPopUp(); // Popup penceresini yeniden oluştur
        }
        // Etiketleri güncelle
        processL.setText("Running");
        subflowValue.setText(subflow);
        pageValue.setText(page);
    }

    public static void updatePopUp(String process) {
        if (!frame.isVisible()) { // Eğer popup penceresi kapalıysa
            createPopUp(); // Popup penceresini yeniden oluştur
        }
        // Etiketleri güncelle
        processL.setText(process);
    }


    //bunu her bir asamadan sonra koyarsak ekranda pause button tiklanirsa projeyi o asamada bekletir
    public static void waitIfPausedTrue() {
        if (paused) {
            updatePopUp("Waiting");
        } else {
            updatePopUp("Running");
        }
        // Proje duraklatıldıysa bekleme
        while (paused) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}




