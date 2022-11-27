package waveUSB.core;

import javax.swing.*;
import java.awt.*;

public class Notification {
    public static final byte ERROR = 0;
    public static final byte QUESTION = 1;
    public static final byte INFO = 2;
    public static final byte SUCCESS = 3;
    public static final byte WARNING = 4;
    private final JFrame frame = new JFrame("Notification");
    private final JLabel text = new JLabel();
    private final JLabel icon = new JLabel();
    private final JButton button = new JButton("");
    private final JButton button2 = new JButton("");

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getText() {
        return text;
    }

    public void setText(String Text) {
        text.setText(Text);
    }

    public JLabel getIcon() {
        return icon;
    }

    public JButton getButton() {
        return button;
    }

    public JButton getButton2() {
        return button2;
    }

    public void setNotificationType(byte notificationType) {
        if (notificationType == 0) {
            icon.setIcon(new ImageIcon("/Users/rishonrishon/Documents/GitHub/WaveUSB/src/resources/images/error.png)"));
            frame.setTitle("Error");
            button.setText("Ok");
            button2.setText("Exit");
        }
        if (notificationType == 1) {
            icon.setIcon(new ImageIcon("/Users/rishonrishon/Documents/GitHub/WaveUSB/src/resources/images/About.png)"));
            frame.setTitle("Question");
            button.setText("Yes");
            button2.setText("No");

        }
        if (notificationType == 2) {
            icon.setIcon(new ImageIcon("/Users/rishonrishon/Documents/GitHub/WaveUSB/src/resources/images/About.png)"));
            frame.setTitle("Information");
            button.setText("Ok");
        }
        if (notificationType == 3) {
            icon.setIcon(new ImageIcon("/Users/rishonrishon/Documents/GitHub/WaveUSB/src/resources/images/success.png)"));
            frame.setTitle("Success");
            button.setText("Ok");

        }
        if (notificationType == 4) {
            icon.setIcon(new ImageIcon("/Users/rishonrishon/Documents/GitHub/WaveUSB/src/resources/images/warning.png)"));
            frame.setTitle("Warning");
            button.setText("Ok");

        }
    }

    public void setFixedSize(int width, int height) {
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setSize(new Dimension(width, height));
    }

    public void setCenter() {
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }
}
