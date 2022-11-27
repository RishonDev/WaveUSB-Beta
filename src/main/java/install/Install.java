package install;

import waveUSB.core.Notification;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

@SuppressWarnings("ALL")
public class Install extends JPanel {

    static JFrame frame = new JFrame("Install Wave USB Writer");
    static JLabel license = new JLabel("You have the rights to modify the code with the developers permission. Follow the instructions given by the authors of this software\n" +
            "to claim rights to the software.\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:\n" +
            "\n" +
            "The above copyright notice and this permission notice shall be included in all\n" +
            "copies or substantial portions of the Software.\n" +
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
            "SOFTWARE.");
    static JPanel ApplicationPanel = new JPanel();
    static JPanel welcomePanel = new JPanel();
    static JButton next = new JButton("Next");
    static JPanel eula = new JPanel();
    static JButton cancel = new JButton("Cancel");
    static JPanel installPanel = new JPanel();
    static JPanel finishPanel = new JPanel();
    static JPanel uninstallPanel = new JPanel();
    static CardLayout layout = new CardLayout();
    static JMenuBar menuBar = new JMenuBar();
    static JMenu File = new JMenu();
    static BufferedWriter writer;

    static JProgressBar progressBar = new JProgressBar();
    static JLabel showTask = new JLabel();
    static JLabel welcomeLabel = new JLabel("Welcome! This installer will guide you through to install WaveUSB Writer");
    static Image background = Toolkit.getDefaultToolkit().getImage("/src/main/java/resources/images/1.jpeg");
    static Notification notification = new Notification();

    static {
        try {
            writer = new BufferedWriter(new FileWriter(".appData"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Install() throws IOException {
    }

    public static void main(String[] args) {
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.setSize(new Dimension(700, 700));
        frame.setMinimumSize(new Dimension(600, 100));
        welcomeLabel.setBounds(100, -290, 600, 600);
        welcomePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        welcomePanel.add(welcomeLabel);
        welcomePanel.setLayout(null);
        welcomePanel.add(next);
        ApplicationPanel.setLayout(layout);
        ApplicationPanel.add(welcomePanel, "1");
        frame.add(ApplicationPanel);
        layout.show(ApplicationPanel, "1");
        notification.show();
        frame.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }


}