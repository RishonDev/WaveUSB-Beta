package javaPack.installGUI;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Install{
    JFrame frame = new JFrame();
    JPanel welcomePanel = new JPanel();
    JPanel installPanel = new JPanel();
    JPanel finishPanel = new JPanel();
    JPanel uninstallPanel = new JPanel();
    CardLayout layout = new CardLayout();
    JMenuBar menuBar = new JMenuBar();
    JMenu File = new JMenu();
    BufferedWriter writer = new BufferedWriter(new FileWriter(".appData"));

    public Install() throws IOException {
    }

    public static void main(String[] args){

    }
}