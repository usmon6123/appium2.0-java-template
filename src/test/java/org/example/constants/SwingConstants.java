package org.example.constants;

import javax.swing.*;

import static org.example.constants.BaseConstants.robot_name;

public class SwingConstants {


    public static JFrame frame;
    public static JLabel processL = new JLabel("Running ");
    public static JLabel robotL = new JLabel(robot_name);
    public static JLabel subflowL, pageL, actionL, durationL, totalTimerL, subflowValue, pageValue, actionValue, durationValue, totalTimerValue;
    public static boolean paused = false;

    public static String homePage = "ana Sayfa";
    public static String cardNumPage = "kart numarası sayfası";
    public static String cardExpDatePage = "kart kullanim tarihi sayfası";

    public static String takeWithdraw = " withdraw işlem al";
    public static String robotStatusControl = "Robot çalıştırma kontrolü";
    public static String getCurrentAmount = "cari bakiye";
    public static String withdrawStatus = "withdraw sql kontrolü";
    public static String goHome = "ana sayfaya dön";
    public static String writeCard = "kart Giris";
    public static String balanceControl = "cari bakiye kontrolü";


}
