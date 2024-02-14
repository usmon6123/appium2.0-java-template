import javax.swing.*;

import java.awt.*;

import static org.example.constants.BaseConstants.*;

public class Main {
    static int count = 0;
    static long startTime;
    static JFrame frame;
    static JLabel processL = new JLabel("Running ");
    static JLabel robotL = new JLabel(robot_name);
    static JLabel subflowL, pageL, actionL, durationL, totalTimerL,subflowValue, pageValue, actionValue, durationValue,  totalTimerValue;

    // Duraklatma durumunu tutacak değişken
    static boolean paused = false;


    public static void main(String[] args) throws InterruptedException {
        createPopUp();

        updatePopUp("Islem Al", "Ana sayfa");


        while (true) {
            System.out.println(count++);
            waitIfPausedTrue();
        }

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
        durationL = new JLabel("Duration: ");
        totalTimerL = new JLabel("Start: ");
        subflowValue = new JLabel("subflow value iiii");
        pageValue = new JLabel("page value");
        actionValue = new JLabel("action value yyyyy");
        durationValue = new JLabel("0");
        totalTimerValue = new JLabel("00:00:00");

        processL.setForeground(new Color(0, 0, 255)); // label1'in yazı rengini mavi (RGB: 0, 0, 255) olarak ayarla

        JPanel headerPanel = doubleLabelPanel(processL, robotL);
        JPanel subflowPanel = doubleLabelPanel(subflowL,subflowValue);
        JPanel pagePanel = doubleLabelPanel(pageL,pageValue);
        JPanel actionPanel = doubleLabelPanel(actionL,actionValue);
        JPanel timerPanel = timerPanel(durationL,durationValue,totalTimerL,totalTimerValue);
        JPanel panel2 = buttonsPanel();






        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(headerPanel,gbc);
        gbc.gridy++;
        panel.add(subflowPanel,gbc);
        gbc.gridy++;
        panel.add(pagePanel,gbc);
        gbc.gridy++;
        panel.add(actionPanel,gbc);
        gbc.gridy++;
        panel.add(timerPanel,gbc);

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
    public static JPanel timerPanel(JLabel durationL, JLabel durationValue, JLabel timerL, JLabel timerValue){
        JPanel panel = new JPanel(new GridLayout(1, 4, 2, 2));
        durationL.setFont(new Font("Arial", Font.ITALIC, 14));
        durationValue.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(durationL);
        panel.add(durationValue);
        timerL.setFont(new Font("Arial", Font.ITALIC, 14));
        timerValue.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(timerL);
        panel.add(timerValue);

        return panel;
    }
    private static JPanel labelsPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#fff8ee"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Dikey düzen



        // Her bir öğeyi panele ekle
        panel.add(Box.createRigidArea(new Dimension(10, 10))); // 10 piksel genişlikte yatay bir boşluk ekle
        panel.add(processL);
        panel.add(robotL);
        panel.add(subflowL);
        panel.add(pageL);
        panel.add(actionL);
        panel.add(durationL);
        panel.add(totalTimerL);
        panel.add(subflowValue);
        panel.add(pageValue);
        panel.add(actionValue);
        panel.add(durationValue);
        panel.add(totalTimerValue);
        return panel;
    }
    private static JPanel buttonsPanel() {

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel2.setBackground(Color.decode("#c9d6fb"));


        // Butonu oluştur
        JButton button = new JButton("Pause");
        JButton homeButton = new JButton("Home");

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



        homeButton.addActionListener(e -> {
            paused = true;
            int result = JOptionPane.showConfirmDialog(null, robot_name + " Robotun şu anda işlemi varsa\n" +
                    " <Yes>\t tıklarsanız işlemdan robot verilerini silerek (işlem açikda kalır) ana sayfaya geri döner !\n" +
                    "<No>\t tıklarsanız 'Əməliyyat xətası', result = 0 diyerek işlemi sonlandırir ve ana sayfaya geri döner !\n" +
                    "<Cancel>\t tıklarsanız Robot bekleme moduna geçer !\n" +
                    "\n" +
                    "Eğer Robotun şu anda işlemi yoksa <Yes> <No> ikisinide tıklarsanız ana sayfaya gelir !", robot_name, JOptionPane.YES_NO_CANCEL_OPTION);

            switch (result) {
                case JOptionPane.YES_OPTION:
                    paused = false;
                    goHome("YES ");
                    break;
                case JOptionPane.NO_OPTION:
                    paused = false;
                    goHome("NO ");
                    break;
                case JOptionPane.CANCEL_OPTION:
                    paused = true;
                    break;
            }
        });

        // Butonu altta ve merkezde ekle
        panel2.add(button);
        panel2.add(homeButton);

        return panel2;
    }


    public static void goHome(String t) {
        System.out.println(t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t + t);
    }

    public static void updatePopUp(String subflow, String page) {
        if (!frame.isVisible()) { // Eğer popup penceresi kapalıysa
            createPopUp(); // Popup penceresini yeniden oluştur
        }
        // Etiketleri güncelle
        processL.setText("Running");
        subflowL.setText(subflow);
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

    public static void updateTimerLabel() {
        // Şu anki zamanı al
        long currentTime = System.currentTimeMillis();

        // Geçen süreyi hesapla
        long elapsedTime = currentTime - startTime;

        // Saat, dakika ve saniyeyi hesapla
        long hours = elapsedTime / (1000 * 60 * 60);
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long seconds = (elapsedTime / 1000) % 60;

        // Zamanı formatla ve timerLabel'a yaz
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        totalTimerValue.setText(timeString);
    }


    public static void showPopUp(String message) {
        JFrame frame = new JFrame("Pop-up");
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.add(label);
        frame.add(panel);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }

}
