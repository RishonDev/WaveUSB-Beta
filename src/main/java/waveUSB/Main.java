package waveUSB;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.util.SystemInfo;
import jadt.utils.Audio.AudioWAV;
import net.samuelcampos.usbdrivedetector.detectors.OSXStorageDeviceDetector;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.io.FilenameUtils;
import waveUSB.core.Archives;
import waveUSB.core.Console;
import waveUSB.core.SoftwareInfo;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import static waveUSB.FileOperations.getRunning_JAR_FilePath;
import static waveUSB.Main.image;

@SuppressWarnings("ALL")
@Getter
public class Main extends Definitions {

    static String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
    static String jar = System.getProperty("java.class.path");
    static String osType;
    static File currentJAR = null;
    static File jarFile = null;
    static File image = null;
    static File usb = null;
    static String filename;

    static {
        try {
            currentJAR = getRunning_JAR_FilePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            jarFile = new File(getRunning_JAR_FilePath(), jar);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Main() throws URISyntaxException {

    }

    public static void main(String[] args) throws URISyntaxException, UnsupportedLookAndFeelException, IOException {
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        chooseUSB.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        isoImageChooser.setFileHidingEnabled(true);
        isoImageChooser.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
        isoImageChooser.setAutoscrolls(true);
        isoImageChooser.setDialogTitle("Choose an image file (*.iso *.dmg *.img *.zip)");
        isoImageChooser.setFileFilter(new FileNameExtensionFilter("ISO image files (*.iso)", "iso"));
        isoImageChooser.addChoosableFileFilter(new FileNameExtensionFilter("Tape Archives with gz(*.tar.gz)", "gz"));
        isoImageChooser.addChoosableFileFilter(new FileNameExtensionFilter("Tape Archives with xz(*.tar.xz)", "xz"));
        isoImageChooser.addChoosableFileFilter(new FileNameExtensionFilter("ZIP files (*.zip)", "zip"));
        isoImageChooser.addChoosableFileFilter(new FileNameExtensionFilter("Tape Atchives(*.tar)", "tar"));
        isoImageChooser.addChoosableFileFilter(new FileNameExtensionFilter("DMG image files (*.dmg)", "dmg"));
        isoImageChooser.addChoosableFileFilter(new FileNameExtensionFilter("IMG Floppy Disk image files (*.img)", "img"));
        initializeDefinitions();
        mainFrame.setIconImages(icons);
        usbDirectory.setForeground(Color.gray);
        chooseUSB.setForeground(Color.gray);
        usbDirectory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usbDirectory.setEditable(true);
                usbDirectory.setText("");
            }
        });
        welcomePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usbDirectory.setEditable(false);
            }
        });
        if (SoftwareInfo.getOS().equals("Windows")) {
            osType = "Windows";
            FlatLightLaf.setup();
            mainFrame.setDefaultLookAndFeelDecorated(true);
        } else if (SoftwareInfo.getOS().equals("Mac OS X")) {
            osType = "Mac OS X";
            menuBar.setName("WaveUSB");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
            console.exec("java -Xdock:name=WaveUSB " + getRunning_JAR_FilePath().getAbsolutePath());
            Desktop desktop = Desktop.getDesktop();

            desktop.setAboutHandler(e ->
                    JOptionPane.showMessageDialog(null, "WaveUSB Version " + SoftwareInfo.getVersion() + "\n" + SoftwareInfo.getAbout())
            );
            desktop.setPreferencesHandler(e ->
                    JOptionPane.showMessageDialog(null, "Preferences dialog")
            );
            desktop.setQuitHandler((e, r) -> {
                        JOptionPane.showMessageDialog(null, "Quit dialog");
                        System.exit(0);
                    }
            );
            Taskbar taskbar = Taskbar.getTaskbar();
            taskbar.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/resources/images/app_icons/ico_macOS11/icon_256x256@2x.png"));
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Wave USB");
            System.setProperty("apple.awt.application.appearance", "system");
            System.setProperty("apple.awt.application.name", "WaveUSB");
            if (SystemInfo.isMacFullWindowContentSupported) {
                mainFrame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
                mainFrame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
            }
            mainFrame.getRootPane().putClientProperty("apple.awt.fullscreenable", true);
        } else if (os.equals("Windows")) {
            osType = "Linux";
        }

        new File(getRunning_JAR_FilePath().getAbsolutePath() + "WaveUSB" + SoftwareInfo.getVersion() + ".jar")
                .renameTo(new File(getRunning_JAR_FilePath().getAbsolutePath() + "WaveUSB.jar"));
        //checks if directories exist
        if (!isoDirectory.exists()) {
            isoDirectory.mkdirs();
        }
        if (!bootloaderDirectory.exists()) {
            bootloaderDirectory.mkdirs();
        }

        //Setting the action when  the button "Select a image file ..." is clicked
        errorLabel.setForeground(Color.red);
        updateApplication.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DownloadFiles.downloadFile(new URL("https://raw.githubusercontent.com/RishonDev/WaveUSB/main/ver.txt"), "./ver.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (new BufferedReader(new FileReader("./ver.txt")).read() != Integer.parseInt(SoftwareInfo.getVersion())) {
                        DownloadFiles.downloadFile(new URL("https://github.com/RishonDev/WaveUSB/releases/download/" + SoftwareInfo.getVersionNumber()
                                + "-" + SoftwareInfo.getBuildMode().toUpperCase()
                                + "/WaveUSB"
                                + SoftwareInfo.getVersionNumber()
                                + "-"
                                + SoftwareInfo.getBuildMode().toUpperCase()
                                + ".jar"), jarFile.getAbsolutePath());
                    } else {
                        NotificationService information = new NotificationService("", "", 0, 0, 0, 0);
                        information.setWindowsProperties("Update Notification", "WaveUSB " + SoftwareInfo.getVersion().toUpperCase() + " is the latest version available"
                                , NotificationService.INFORMATION, 100, 100, 300, 100);
                        information.init();
                        information.centre();
                        information.playSound();
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        closeApplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = isoImageChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    imageFileDirectory.setText(isoImageChooser.getSelectedFile().getAbsolutePath());
                    filename = isoImageChooser.getSelectedFile().getName();
                }
            }
        });
        //Setting the Actions to do when the button"Other..." is clicked
        otherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usb = new File(usbDirectory.getText());
                if (usbDirectory.getText().equals("") && usbDirectory.getText().equals("Enter or Choose the USB path.."))
                    JOptionPane.showMessageDialog(new JFrame(), "Error: No USB Path Selected");
                if (usb.exists()) {
                    if (!OSXStorageDeviceDetector.getInstance().getStorageDevices().toString().contains(usb.getName())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error:The directory selected is not a external device.");
                    } else
                        layout.show(applicationPanel, "5");
                } else JOptionPane.showMessageDialog(new JFrame(), "Error:USB Doesn't exist");
            }
        });
        //Back button for the macOS Panel
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                layout.show(applicationPanel, "1");
            }
        });
        //Setting What to do when  the button "macOS " Button is clicked
        macOSButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usb = new File(usbDirectory.getText());
                if (usbDirectory.getText().equals("") && usbDirectory.getText().equals("Enter or Choose the USB path.."))
                    JOptionPane.showMessageDialog(new JFrame(), "Error: No USB Path Selected");
                if (usb.exists()) {
                    if (!OSXStorageDeviceDetector.getInstance().getStorageDevices().toString().contains(usb.getName())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error:The directory selected is not a external device.");
                    } else
                        layout.show(applicationPanel, "2");
                } else JOptionPane.showMessageDialog(new JFrame(), "Error:USB Doesn't exist");
            }
        });
        //Setting What to do when the button "Windows " Button is clicked
        windowsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setLocationRelativeTo(null);
                usb = new File(usbDirectory.getText());
                if (usbDirectory.getText().equals("") && usbDirectory.getText().equals("Enter or Choose the USB path.."))
                    JOptionPane.showMessageDialog(new JFrame(), "Error: No USB Path Selected");
                if (usb.exists()) {
                    if (!OSXStorageDeviceDetector.getInstance().getStorageDevices().toString().contains(usb.getName())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error:The directory selected is not a external device.");
                    } else
                        layout.show(applicationPanel, "4");
                } else JOptionPane.showMessageDialog(new JFrame(), "Error:USB Doesn't exist");
            }
        });
        //Setting what to do when the button "back" on the

        linuxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usb = new File(usbDirectory.getText());
                if (usbDirectory.getText().equals("") && usbDirectory.getText().equals("Enter or Choose the USB path.."))
                    JOptionPane.showMessageDialog(new JFrame(), "Error: No USB Path Selected");
                if (usb.exists()) {
                    if (!OSXStorageDeviceDetector.getInstance().getStorageDevices().toString().contains(usb.getName())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error:The directory selected is not a external device.");
                    } else
                        layout.show(applicationPanel, "3");
                } else JOptionPane.showMessageDialog(new JFrame(), "Error:USB Doesn't exist");
                mainFrame.setLocationRelativeTo(null);
            }
        });
        windows10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    desktopActions.browse(new URI(Constants.Windows10));
                    layout.show(applicationPanel, "5");
                    othersChoose.setText("First Download the image to the Directory " + SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/\n Then Click Continue.");
                    isoImageChooser.setFileHidingEnabled(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        windows11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    desktopActions.browse(new URI(Constants.Windows11));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        windows8_64.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    desktopActions.browse(new URI(Constants.Windows8));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        windows8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    desktopActions.browse(new URI(Constants.Windows8));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        windows10_64.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    desktopActions.browse(new URI(Constants.Windows10));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        macOS_1015.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        macOS_1014.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new File(Constants.appData + "/ISO/Mojave.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/Mojave.iso"), (usbDirectory.getText()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.macOS10_14), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Mojave.iso");
                        Archives.writeISO(Constants.appData + "/ISO/Mojave.iso", (usbDirectory.getText()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Mojave.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        macOS1013.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new File(Constants.appData + "/ISO/HighSierra.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/HighSierra.iso"), (usbDirectory.getText()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.macOS10_13), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/HighSierra.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/HighSierra.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        macOS_11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        next2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (new File(usbDirectory.getText()).exists()) {
                    if (new File(imageFileDirectory.getText()).exists()) {
                        //try {
                        othersChoose.setText("Please choose an image file");
                        System.out.println(FilenameUtils.getExtension(filename));
                        if (FilenameUtils.getExtension(filename).equals("iso")) {

                            try {

                                Archives.writeISO(imageFileDirectory.getText(), usbDirectory.getText());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (FilenameUtils.getExtension(filename).equals("gz")) {
                            try {
                                Archives.ungz(new File(imageFileDirectory.getText()), new File(usbDirectory.getText()));
                                if (FilenameUtils.getExtension(usbDirectory.getText() + new String(filename).replaceAll(".gz", "")).equals("tar")) {
                                    Archives.untar(new File(usbDirectory.getText() + new String(filename).replaceAll(".gz", "")), new File(usbDirectory.getText()));
                                    new File(usbDirectory.getText() + new String(filename).replaceAll(".gz", "")).delete();
                                }
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (ArchiveException ex) {
                                try {
                                    Archives.untar(new File(usbDirectory.getText() + new String(filename).replaceAll(".gz", "")), new File(usbDirectory.getText()));
                                } catch (IOException exc) {
                                    throw new RuntimeException(exc);
                                } catch (ArchiveException exc) {
                                    throw new RuntimeException(exc);
                                }
                            }
                        }
                        if (FilenameUtils.getExtension(filename).equals("zip")) {
                            Archives.unzip(imageFileDirectory.getText(), usbDirectory.getText());
                        }
                        if (FilenameUtils.getExtension(filename).equals("xz")) {
                            try {
                                Archives.unxz(new File(imageFileDirectory.getText()), new File(usbDirectory.getText()));
                            } catch (IOException ex) {
                                try {
                                    Archives.untar(new File(imageFileDirectory.getText()), new File(usbDirectory.getText()));
                                } catch (IOException exc) {
                                    throw new RuntimeException(exc);
                                } catch (ArchiveException exc) {
                                    throw new RuntimeException(exc);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showInternalMessageDialog(mainFrame, "USB Drive  Does Not Exist. "
                                + "Please Go Back And Try Again");
                    }
                } else {
                    othersPane.add(errorLabel);
                    errorLabel.setText("Error: USB not found, please go back and try again.");
                }
            }
        });
        ubuntuLTS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/Ubuntu.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/Ubuntu.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        //float downloadSize = (float) (FileOperations.getFileSize(new URL(Constants.ubuntu)) / Math.pow(1024,3));
                        DownloadFiles.downloadFile(new URL(Constants.ubuntu), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Ubuntu.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Ubuntu.iso");
                    layout.show(applicationPanel, "11");
                }
            }

        });
        ubuntuServerLTS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setResizable(false);
                if (new File(Constants.appData + "/ISO/UbuntuServer.iso").exists()) {
                    try {

                        Archives.writeISO(Constants.appData + "/ISO/UbuntuServer.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.ubuntuServer), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/UbuntuServer.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/UbuntuServer.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        arch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/Arch.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/Arch.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.arch), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Arch.iso");
                        Archives.writeISO(Constants.appData + "/ISO/Arch.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Arch.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        deepin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/Deepin.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/Deepin.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.deepin), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Deepin.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Deepin.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        debian64.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/Debian64Bit.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/Debian64Bit.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.debian64), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Debian64Bit.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Debian64Bit.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        debian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/Debian32Bit.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/Debian32Bit.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.debian), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Debian32Bit.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Debian32Bit.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        debianNet64.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/Debian_Net_64Bit.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/Debian_Net_64Bit.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.debianNet64), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Debian_Net_64Bit.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Debian_Net_64Bit.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        debianNet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/Debian_Net.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/Debian_Net.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.debianNet), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/DebianNet.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Debian_Net.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        fedoraWorkspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/FedoraWorkspace.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/FedoraWorkspace.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.fedoraWorkspace), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraWorkspace.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraWorkspace.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        fedoraWorkspace64.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/FedoraWorkspace64Bit.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/FedoraWorkspace64Bit.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.fedoraWorkspace64), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraWorkspace64Bit.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraWorkspace64Bit.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        fedoraServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/FedoraServer.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/FedoraServer.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        mainFrame.setMinimumSize(new Dimension(650, 600));
                        DownloadFiles.downloadFile(new URL(Constants.fedoraServer), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraServer.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraServer.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        fedoraServer_ARM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/FedoraServer_ARM.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/FedoraServer_ARM.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.fedoraServer_ARM), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraServer_ARM.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraServer_ARM.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });

        fedoraServerNet64.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/FedoraServerNet64.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/FedoraServerNet64.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.fedoraServerNet), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraServerNet64.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/FedoraServerNet64.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        manjaroKDE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/ManjaroKDE.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/ManjaroKDE.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.manjaroKDE), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ManjaroKDE.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ManjaroKDE.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        manjaroXFCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/ManjaroXFCE.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/ManjaroXFCE.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.manjaroXFCE), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ManjaroXFCE.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ManjaroKDE.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        manjaroGnome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/ManjaroGnome.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/ManjaroGnome.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.manjaroGnome), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ManjaroGnome.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ManjaroGnome.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        blackArch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/BlackArch.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/BlackArch.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.blackArch), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/BlackArch.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/BlackArch.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        blackArchMinimum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/BlackArchMinimum.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/BlackArchMinumum.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.blackArchMinimum), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/BlackArchMinimum.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/BlackArchMinimum.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        blackArchNet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/BlackArchNet.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/BlackArchNet.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.blackArchNet), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/BlackArchNet.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/BlackArchNet.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        elementaryOS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/ElementaryOS.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/ElementaryOS.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.elementaryOS), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ElementaryOS.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/ElementaryOS.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        linuxMintCinnamon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new File(Constants.appData + "/ISO/LinuxMintCinnamon.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/LinuxMintCinnamon.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        mainFrame.setMinimumSize(new Dimension(650, 600));
                        DownloadFiles.downloadFile(new URL(Constants.linuxMintCinnamon), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/LinuxMintCinnamon.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/LinuxMintCinnamon.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        linuxMintXfce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/LinuxMintXFCE.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/LinuxMintXFCE.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.linuxMintXFCE), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/LinuxMintXFCE.iso");
                        Archives.writeISO((usbDirectory.getText()), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/LinuxMintXFCE.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        linuxMintMate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/LinuxMintMATE.iso").exists()) {
                    try {
                        Archives.writeISO((Constants.appData + "/ISO/LinuxMintMATE.iso"), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.linuxMintMate), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/LinuxMintMATE.iso");
                        Archives.writeISO((usbDirectory.getText()), (usbDirectory.getText()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/LinuxMintMATE.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        solusBudgie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/LinuxMintMATE.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/LinuxMintMATE.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.solusBudgie), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/SolusBudgie.iso");
                        Archives.writeISO(usbDirectory.getText(), usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/SolusBudgie.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        solusGnome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/solusGnome.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/solusGnome.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.solusGnome), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/LinuxMintMATE.iso");
                        Archives.writeISO(Constants.appData + "/ISO/solusGnome.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/SolusGnome.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        solusKDE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/SolusGnome.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/SolusKDE.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.solusKDE), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/SolusKDE.iso");
                        Archives.writeISO(Constants.appData + "/ISO/SolusKDE.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/SolusGnome.iso");
                    layout.show(applicationPanel, "11");
                }
            }

        });
        solusMATE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMinimumSize(new Dimension(650, 600));
                if (new File(Constants.appData + "/ISO/LinuxMintMATE.iso").exists()) {
                    try {
                        Archives.writeISO(Constants.appData + "/ISO/LinuxMintMATE.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        DownloadFiles.downloadFile(new URL(Constants.solusMATE), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/SolusMATE.iso");
                        Archives.writeISO(Constants.appData + "/ISO/LinuxMintMATE.iso", usbDirectory.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/SolusMATE.iso");
                    layout.show(applicationPanel, "11");
                }
            }
        });
        back4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isoImageChooser.setFileHidingEnabled(true);
                othersChoose.setText("Please choose an image file");
                layout.show(applicationPanel, "1");
            }
        });

        back2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                layout.show(applicationPanel, "1");
                mainFrame.setLocationRelativeTo(null);
            }
        });
        back3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(applicationPanel, "1");
                mainFrame.setMinimumSize(new Dimension(650, 600));
            }
        });
        selectUSB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (os.equals("Mac OS X")) chooseUSB.setCurrentDirectory(new File("/Volumes"));
                else chooseUSB.setCurrentDirectory(new File("/dev"));
                int response = chooseUSB.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    usbDirectory.setText(chooseUSB.getSelectedFile().getAbsolutePath());
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        debianBasedDistros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(applicationPanel, "12");
                miscLinuxPane.remove(back9);
                archPane.remove(back9);
                debianPane.add(back9);
                debianPane.add(linuxChoose2);
                linuxChoose2.setBounds(125, 50, 400, 30);
            }
        });
        back9.setBounds(20, 530, 100, 30);
        back9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(applicationPanel, "3");
            }
        });
        archBasedDistros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archPane.add(back9);
                miscLinuxPane.remove(back9);
                debianPane.remove(back9);
                linuxChoose3.setBounds(125, 50, 400, 30);
                archPane.add(linuxChoose3);
                layout.show(applicationPanel, "13");
            }
        });
        miscDistributions.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                linuxChoose.setBounds(125, 50, 400, 30);
                miscLinuxPane.add(back9);
                archPane.remove(back9);
                debianPane.remove(back9);
                miscLinuxPane.add(linuxChoose4);
                layout.show(applicationPanel, "15");
                linuxChoose4.setBounds(125, 50, 400, 30);
            }
        });
        mainFrame.setJMenuBar(menuBar);
        //Adding the components to The Welcome screen
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        welcomePane.setLayout(null);
        welcomePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        welcomePane.add(welcomeLabel);
        welcomePane.add(chooseOS);
        welcomePane.add(macOSButton);
        welcomePane.add(windowsButton);
        welcomePane.add(linuxButton);
        welcomePane.add(otherButton);
        welcomePane.add(quitButton);
        welcomePane.add(usbDirectory);
        welcomePane.add(selectUSB);
        welcomePane.add(formatUSB);
        welcomePane.add(installBootloaderOption);

        welcomeLabel.setBounds(250, 40, 500, 30);
        chooseOS.setBounds(50, 80, 500, 30);
        macOSButton.setBounds(250, 130, 100, 50);
        windowsButton.setBounds(250, 180, 100, 50);
        linuxButton.setBounds(250, 230, 100, 50);
        otherButton.setBounds(250, 280, 100, 50);
        quitButton.setBounds(30, 530, 100, 30);
        usbDirectory.setBounds(250, 330, 220, 30);
        selectUSB.setBounds(470, 330, 125, 30);
        formatUSB.setBounds(250, 360, 125, 30);
        installBootloaderOption.setBounds(250, 390, 175, 30);
        //Setting the layout to  null so that the components can be freely placed on the screen and adding the components to the macOS screen
        macOSPane.setLayout(null);
        macOSPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        chooseMacOSLabel.setBounds(250, 50, 500, 30);
        macOSPane.add(chooseMacOSLabel);
        macOSPane.add(macOS_13);
        macOSPane.add(macOS_11);
        macOSPane.add(macOS_12);
        macOSPane.add(macOS_1015);
        macOSPane.add(macOS_1014);
        macOSPane.add(macOS1013);
        macOSPane.add(back);
        macOS_13.setBounds(150, 100, 400, 50);
        macOS_12.setBounds(150, 150, 400, 50);
        macOS_11.setBounds(150, 200, 400, 50);
        macOS_1015.setBounds(150, 250, 400, 50);
        macOS_1014.setBounds(150, 300, 400, 50);
        macOS1013.setBounds(150, 350, 400, 50);
        back.setBounds(30, 530, 100, 30);
        //Setting the Font of "Choose yyour macOS Version"
        chooseMacOSLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        //Setting the layout to null so that the components can be freely placed on the frame and adding them to the Linux Window
        linuxPane.setLayout(null);
        linuxPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Adding the components to the Linux Window
        linuxPane.add(linuxChoose);
        linuxPane.add(debianBasedDistros);
        linuxPane.add(miscDistributions);
        linuxPane.add(archBasedDistros);

        linuxPane.add(back2);
        debianPane.add(ubuntuLTS);
        debianPane.add(ubuntuServerLTS);
        debianPane.add(deepin);
        debianPane.add(debian);
        debianPane.add(debian64);
        debianPane.add(linuxMintCinnamon);
        debianPane.add(linuxMintMate);
        debianPane.add(linuxMintXfce);
        debianPane.add(elementaryOS);
        debianPane.add(debianNet);
        debianPane.add(debianNet64);
        archPane.add(arch);
        archPane.add(blackArch);
        archPane.add(blackArchMinimum);
        archPane.add(blackArchNet);
        archPane.add(manjaroGnome);
        archPane.add(manjaroKDE);
        archPane.add(manjaroXFCE);
        miscLinuxPane.add(fedoraWorkspace);
        miscLinuxPane.add(fedoraWorkspace64);
        miscLinuxPane.add(fedoraServer);
        miscLinuxPane.add(fedoraServer_ARM);
        miscLinuxPane.add(fedoraServerNet64);
        miscLinuxPane.add(fedoraServerNet_ARM);
        miscLinuxPane.add(solusGnome);
        miscLinuxPane.add(solusBudgie);
        miscLinuxPane.add(solusKDE);
        miscLinuxPane.add(solusMATE);
        //Setting the size and shape of the components in the Linux Pane
        debianBasedDistros.setBounds(125, 125, 300, 50);
        archBasedDistros.setBounds(125, 175, 300, 50);
        miscDistributions.setBounds(125, 225, 300, 50);
        debianPane.setLayout(null);
        debianPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        linuxChoose.setBounds(125, 50, 400, 30);
        debian.setBounds(125, 180, 300, 50);
        debian64.setBounds(125, 230, 300, 50);
        debianNet.setBounds(125, 280, 300, 50);
        debianNet64.setBounds(125, 330, 300, 50);
        deepin.setBounds(125, 80, 300, 50);
        elementaryOS.setBounds(125, 430, 300, 50);
        linuxMintXfce.setBounds(125, 380, 300, 50);
        linuxMintCinnamon.setBounds(125, 280, 300, 50);
        linuxMintMate.setBounds(125, 330, 300, 50);
        ubuntuLTS.setBounds(125, 80, 300, 50);
        ubuntuServerLTS.setBounds(125, 130, 300, 50);
        archPane.setLayout(null);
        arch.setBounds(145, 80, 300, 50);
        blackArchNet.setBounds(135, 130, 320, 50);
        blackArchMinimum.setBounds(145, 180, 300, 50);
        blackArch.setBounds(145, 230, 300, 50);
        manjaroGnome.setBounds(145, 280, 300, 50);
        manjaroKDE.setBounds(145, 330, 300, 50);
        manjaroXFCE.setBounds(145, 380, 300, 50);
        miscLinuxPane.setLayout(null);
        fedoraServer_ARM.setBounds(145, 80, 300, 50);
        fedoraServerNet64.setBounds(145, 130, 300, 50);
        fedoraServerNet_ARM.setBounds(145, 180, 300, 50);
        fedoraWorkspace.setBounds(145, 230, 300, 50);
        fedoraWorkspace64.setBounds(145, 280, 300, 50);
        fedoraServer.setBounds(145, 330, 300, 50);
        solusGnome.setBounds(145, 380, 300, 50);
        solusBudgie.setBounds(145, 430, 300, 50);
        solusKDE.setBounds(145, 480, 300, 50);
        solusMATE.setBounds(145, 530, 300, 50);
        back2.setBounds(20, 550, 100, 30);
        //Setting the layout to  null so that the components can be freely placed on the frame and adding them to the "Windows" frame.
        windowsPane.setLayout(null);
        windowsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //Adding the components to the "Windows" frame
        windowsPane.add(windowsChose);
        windowsPane.add(windowsXP);
        windowsPane.add(windowsXP_64);
        windowsPane.add(windows8);
        windowsPane.add(windows8_64);
        windowsPane.add(windows10);
        windowsPane.add(windows10_64);
        windowsPane.add(windows11);
        windowsPane.add(back3);
        //Setting the size and shape of the Components of the Windows frame
        windowsChose.setBounds(225, 50, 300, 30);
        windowsXP.setBounds(225, 80, 200, 50);
        windowsXP_64.setBounds(225, 130, 200, 50);
        windows8.setBounds(225, 180, 200, 50);
        windows8_64.setBounds(225, 230, 200, 50);
        windows10.setBounds(225, 280, 200, 50);
        windows10_64.setBounds(225, 330, 200, 50);
        windows11.setBounds(225, 380, 200, 50);
        back3.setBounds(30, 530, 75, 30);
        //Setting the layout to  null so that the components can be freely placed on the frame and adding them into the other images pane
        othersPane.setLayout(null);
        othersPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //Adding the components to the other images pane
        othersPane.add(supportedFileTypes);
        othersPane.add(othersChoose);
        othersPane.add(imageFileDirectory);
        othersPane.add(selectFile);
        othersPane.add(next2);
        othersPane.add(back4);
        othersPane.add(next1);
        //Setting size and location of the components in the other images pane
        othersChoose.setBounds(225, 30, 800, 30);
        selectFile.setBounds(450, 100, 150, 30);
        imageFileDirectory.setBounds(125, 100, 250, 40);
        supportedFileTypes.setBounds(125, 350, 500, 40);
        next2.setBounds(450, 530, 150, 30);
        back4.setBounds(30, 530, 100, 30);
        //Adding the components to the "write to USB pane"
        progressBar.setBounds(300, 300, 600, 100);
        progressBar.setBorderPainted(true);
        progressScreen.setLayout(null);
        progressBar.setForeground(Color.blue);
        progressBar.setBounds(30, 530, 600, 50);
        progressScreen.add(progressBar);

        //Adding the macOS EULA Components to it's panel
        macOSEULA.setLayout(null);
        jScrollPane.add(jTextArea);
        jScrollPane.setVerticalScrollBar(jScrollBar);
        jInternalFrame.add(jScrollPane);
        macOSEULA.add(jInternalFrame);
        jInternalFrame.setBounds(300, 300, 300, 300);
        //Setting the Application Frame and initializing the application
        applicationPanel.setLayout(null);
        applicationPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        applicationPanel.setLayout(layout);
        //Adding to the file menu
        fileMenu.add(closeApplication);
        //help menu
        helpMenu.add(updateApplication);
        //Showing the welcome screen
        menuBar.add(fileMenu);
        layout.show(applicationPanel, "1");
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        menuBar.setBounds(0, 0, 650, 30);
        //Setting the bootloader option
        chooseBootloaderPane.add(sysLinuxButton);
        chooseBootloaderPane.add(cloverButton);
        chooseBootloaderPane.add(noneBootloaderOption);
        chooseBootloaderPane.add(bootloaderNext);
        //Setting the position  in the frame
        chooseBootloaderPane.setLayout(null);
        chooseBootloaderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        sysLinuxButton.setBounds(50, 0, 400, 30);
        cloverButton.setBounds(50, 50, 400, 30);
        noneBootloaderOption.setBounds(50, 100, 600, 30);
        bootloaderNext.setBounds(30, 530, 100, 30);
        //Adding The Panels to the Main Application Through Card Layout
        applicationPanel.add(welcomePane, "1");
        applicationPanel.add(macOSPane, "2");
        applicationPanel.add(linuxPane, "3");
        applicationPanel.add(windowsPane, "4");
        applicationPanel.add(othersPane, "5");
        applicationPanel.add(progressScreen, "6");
        applicationPanel.add(finishedScreen, "8");
        applicationPanel.add(macOSEULA, "10");
        applicationPanel.add(chooseBootloaderPane, "11");
        applicationPanel.add(debianPane, "12");
        applicationPanel.add(archPane, "13");
        applicationPanel.add(miscLinuxPane, "15");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Finishing initialisation of the Application.
        mainFrame.add(applicationPanel);
        mainFrame.setSize(new Dimension(650, 600));
        mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainFrame.setMinimumSize(new Dimension(650, 600));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        //layout.show(applicationPanel, "6");
        layout.show(applicationPanel, "1");

    }

    //This is Only for macOS users.
    public static void installApp(String packageFile) throws Exception {
        console.exec("sudo installer -pkg " + Constants.appData + "/" + packageFile + " -target CurrentUserHomeDirectory");
    }


    public static void exit() {
        System.exit(0);
    }
    //function to exit the program

    public static void startJobs(byte osType, boolean canFomat, boolean canInstallBootloader) {
        //macOS 13
        if (osType == 1) {

        }
        //macOS 12
        else if (osType == 2) {

        }
        //macOS 11
        else if (osType == 3) {

        }
        //macOS 10.15
        else if (osType == 4) {
            if (new File(Constants.appData + "/ISO/Catalina.iso").exists()) {
                try {
                    Archives.writeISO(Constants.appData + "/ISO/Catalina.iso", usbDirectory.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Error writing ISO File:" + ex.getMessage());
                }
            } else {
                try {
                    DownloadFiles.downloadFile(new URL(Constants.macOS10_15), SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Catalina.iso");
                    Archives.writeISO(Constants.appData + "/ISO/Catalina.iso", (usbDirectory.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                image = new File(SoftwareInfo.getHomeDirectory() + "/.WaveUSB/ISO/Catalina.iso");
                layout.show(applicationPanel, "11");
            }
        }

    }

    public String getJavaBin() {
        return javaBin;
    }
}

@SuppressWarnings("ALL")
class NotificationService {
    public static String Title = null;
    public static String DisplayText = null;
    public static int posx;
    public static int posy;
    public static int width;
    public static int height;
    public static byte ERROR = 0;
    public static byte WARNING = 1;
    public static byte QUESTION = 2;
    public static byte INFORMATION = 3;
    static AudioWAV errorSound;
    static AudioWAV warningSound;
    static AudioWAV infoSound;
    static AudioWAV QuestionSound;
    static JLabel DisplayTextLabel = new JLabel("Lorem Ipsum");
    static JFrame ApplicationWindow = new JFrame();
    static JButton ok = new JButton("Lorem Ipsum...");
    static JButton cancel = new JButton("Lorem Ipsum...");
    static JLabel Icon_IMG = new JLabel();
    static AudioWAV wavePlayer;
    static byte errorType;

    static {
        try {
            errorSound = new AudioWAV("./src/resources/audio/errorSound.wav");
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            warningSound = new AudioWAV("./src/resources/audio/WarningSound.wav");
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            infoSound = new AudioWAV("./src/resources/audio/InformationSound.wav");
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            QuestionSound = new AudioWAV("./src/resources/audio/QuestionSound.wav");
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NotificationService(String Title, String DisplayText, int posx, int posy, int width, int height) {
        ApplicationWindow.setTitle(Title);
        ApplicationWindow.setBounds(posx, posy, width, height);
        DisplayTextLabel.setText(DisplayText);
        NotificationService.DisplayText = DisplayText;
        NotificationService.posx = posx;
        NotificationService.posy = posy;
        NotificationService.width = width;
        NotificationService.Title = Title;
        NotificationService.height = height;
    }

    public static void setWindowsProperties(String title, String displayText, byte errorType, int Posx, int Posy, int Width, int Height) {
        ApplicationWindow.setTitle(Title);
        ApplicationWindow.setBounds(posx, posy, width, height);
        DisplayTextLabel.setText(DisplayText);
        Title = title;
        DisplayText = displayText;
        posx = Posx;
        posy = Posy;
        width = Width;
        height = Height;
    }

    public static void centre() {
        ApplicationWindow.setLocationRelativeTo(null);
    }

    public static void setIcon(String filePath) {
        Icon_IMG.setIcon(new ImageIcon(filePath));
    }

    public static void deinit() {
        ApplicationWindow.setVisible(false);
        ok.setText(null);
        cancel.setText(null);
        Icon_IMG.setText(null);
        ApplicationWindow.setTitle(null);
        ApplicationWindow.setVisible(false);

    }

    public static void init() {

        ApplicationWindow.setLocationRelativeTo(null);
        ApplicationWindow.setVisible(true);
    }

    public static void playSound() {

        if (errorType == NotificationService.ERROR) {
            errorSound.start();
        } else if (errorType == NotificationService.QUESTION) {
            QuestionSound.start();
        } else if (errorType == NotificationService.WARNING) {
            warningSound.start();
            for (int i = 0; i < 1000; i++) {
            }
            warningSound.stop();
        } else if (errorType == NotificationService.INFORMATION) {
            infoSound.start();
            for (int i = 0; i < 1000; i++) {
            }
            infoSound.stop();
        }
    }

    public static JLabel getDisplayTextLabel() {
        return DisplayTextLabel;
    }

    public static JFrame getApplicationWindow() {
        return ApplicationWindow;
    }

    public static JButton getOk() {
        return ok;
    }

    public static JButton getCancel() {
        return cancel;
    }

    public static JLabel getIcon_IMG() {
        return Icon_IMG;
    }
}

class DownloadFiles {
    public static void downloadFile(URL url, String outputFileName) throws IOException {
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

    public static void downloadFileWithTotalDownloadedData(String Url, String DirectoryOutputFileName) throws MalformedURLException {
        URL url = new URL(Url);
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(DirectoryOutputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            float size = FileOperations.getFileSizeInMegabytes(DirectoryOutputFileName), downloadedData = 0.0F;
            while (downloadedData <= size) {
                Main.getStatusLabel().setText("Downloaded " + FileOperations.getFileSizeInMegabytes(DirectoryOutputFileName) + "MB out of " + size + "MB");
                if (FileOperations.getFileSizeInMegabytes(image.getAbsolutePath()) <= size)
                    downloadedData = FileOperations.getFileSizeInMegabytes(image.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void downloadThroughWget(String URL, String args) throws IOException {
        Console.wget(args + URL + "-");
    }

    public static void downloadThroughWget(String URL, String args, String outputDir) throws IOException {

    }


}

class CoreOperations {
    public static void createAppDirsIfNeeded() {

    }
}

class FileOperations {
    public static File getRunning_JAR_FilePath() throws URISyntaxException {
        return new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI());
    }

    public static int getFileSize(URL url) {
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("HEAD");
            }
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).disconnect();
            }
        }
    }

    public static long getFileSizeInBits(String Directory) {
        return new File(Directory).length() * 8;
    }

    public static long getFileSizeInBytes(String Directory) {
        return new File(Directory).length();
    }

    public static long getFileSizeInKilobytes(String Directory) {
        return new File(Directory).length() / 1024;
    }

    public static long getFileSizeInMegabytes(String Directory) {
        return (long) (new File(Directory).length() / Math.pow(1024, 2));
    }

    public static long getFileSizeInGigabytes(String Directory) {
        return (long) (new File(Directory).length() / Math.pow(1024, 3));
    }

    public static long convertToBits(long value, String inputValueUnit) {
        return switch (inputValueUnit) {
            case "Bytes" -> value * 8;
            case "Kilobytes" -> value * 1024 * 8;
            case "Megabytes" -> value * 1024 * 1024 * 8;
            case "Gigabytes" -> value * 1024 * 1024 * 1024 * 8;
            default -> 0;
        };
    }

    public static long convertToBytes(long value, String inputValueUnit) {
        return switch (inputValueUnit) {
            case "Bits" -> value / 8;
            case "Kilobytes" -> value * 1024;
            case "Megabytes" -> value * 1024 * 1024;
            case "Gigabytes" -> value * 1024 * 1024 * 1024;
            default -> 0;
        };
    }

    public static long convertToKilobytes(long value, String inputValueUnit) {
        long size = switch (inputValueUnit) {
            case "Bits" -> value / 8 / 1024;
            case "Bytes" -> value / 1024;
            case "Megabytes" -> value * 1024;
            case "Gigabytes" -> value * 1024 * 1024;
            default -> 0;
        };
        return size;
    }


    public static long convertToMegabytes(long value, String inputValueUnit) {
        long size = switch (inputValueUnit) {
            case "Bits" -> value / 8 / 1024 / 1024;
            case "Bytes" -> value / 1024 / 1024;
            case "Kilobytes" -> value / 1024;
            case "Gigabytes" -> value * 1024;
            default -> 0;
        };
        return size;
    }

    public static long convertToGigabytes(long value, String inputValueUnit) {
        long size = switch (inputValueUnit) {
            case "Bits" -> value / 8 / 1024 / 1024 / 1024;
            case "Bytes" -> value / 1024 / 1024 / 1024;
            case "Kilobytes" -> value / 1024 / 1024;
            case "Megabytes" -> value / 1024;
            default -> 0;
        };
        return size;
    }


}

class Settings {
    static BufferedWriter writer;
    static JFrame frame = new JFrame("Settings");
    static JRadioButton png = new JRadioButton("");
    static JRadioButton png1 = new JRadioButton("");
    static JRadioButton png2 = new JRadioButton("");
    static JRadioButton png3 = new JRadioButton("");
    static JRadioButton png4 = new JRadioButton("");
    static JRadioButton png5 = new JRadioButton("");
    static JRadioButton png6 = new JRadioButton("");
    static JRadioButton png7 = new JRadioButton("");
    static JRadioButton png8 = new JRadioButton("l");
    static JPanel ApplicationPanel = new JPanel();
    static JButton save = new JButton("Save Settings");

    static {
        try {
            writer = new BufferedWriter(new FileWriter(".settings"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Settings() throws IOException {
    }

    public static void init() throws IOException {
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (png.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/1.jpeg");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (png1.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/2.jpeg");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (png2.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/3.jpeg");
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (png3.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/4.jpeg");
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (png4.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/5.jpeg");
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (png5.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/6.jpeg");
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (png6.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/7.jpeg");
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (png7.isSelected()) {
                    try {
                        writer.write("bg=" + SoftwareInfo.getHomeDirectory() + ".waveUSB/8.jpeg");
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        });
        writer.write("bg=");
        writer.close();
        png.setBounds(225, 20, 100, 30);
        ApplicationPanel.add(png);
        png1.setBounds(225, 50, 100, 30);
        ApplicationPanel.add(png1);
        png2.setBounds(225, 80, 100, 30);
        ApplicationPanel.add(png2);
        png3.setBounds(225, 110, 100, 30);
        ApplicationPanel.add(png3);
        png4.setBounds(225, 140, 100, 30);
        ApplicationPanel.add(png4);
        png5.setBounds(225, 170, 100, 30);
        ApplicationPanel.add(png5);
        png6.setBounds(225, 200, 100, 30);
        ApplicationPanel.add(png6);
        png7.setBounds(225, 230, 100, 30);
        ApplicationPanel.add(png7);
        png8.setBounds(225, 260, 100, 30);
        ApplicationPanel.add(png8);
        save.setBounds(300, 300, 200, 30);
        ApplicationPanel.add(save);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        ApplicationPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        ApplicationPanel.setLayout(null);
        frame.add(ApplicationPanel);
        frame.setVisible(true);
    }
}
