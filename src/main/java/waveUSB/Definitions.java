package waveUSB;

import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.unmounters.LinuxStorageDeviceUnmounter;
import net.samuelcampos.usbdrivedetector.unmounters.OSXStorageDeviceUnmounter;
import net.samuelcampos.usbdrivedetector.unmounters.WindowsStorageDeviceUnmounter;
import waveUSB.core.SoftwareInfo;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class Definitions {

    static final Runtime console = Runtime.getRuntime();
    static File bootloaderDirectory = new File(SoftwareInfo.getAppDataDirectory() + "/Bootloaders");
    static File isoDirectory = new File(SoftwareInfo.getAppDataDirectory() + "/ISO");
    //Layout Declarations
    static CardLayout layout = new CardLayout();
    //Application Window And Pane Declarations
    static JPanel applicationPanel = new JPanel();
    static JPanel finishedScreen = new JPanel();//ending screen
    static JPanel linuxPane = new JPanel();
    static JFrame mainFrame = new JFrame("Wave USB Image Writer");
    static JPanel macOSPane = new JPanel();
    static JPanel macOSEULA = new JPanel();
    static JPanel othersPane = new JPanel();
    static JPanel welcomePane = new JPanel();
    static JPanel windowsPane = new JPanel();
    static JPanel chooseBootloaderPane = new JPanel();
    static JPanel progressScreen = new JPanel();
    //Linux Distros.
    static JPanel debianPane = new JPanel();
    static JPanel archPane = new JPanel();
    static JPanel miscLinuxPane = new JPanel();
    //Labels Used in the application
    static JLabel supportedFileTypes = new JLabel("Supported file types: (*.iso)(*.img)(*tar.gz)(*.zip)");
    static JLabel statusLabel = new JLabel("Status");
    static JLabel chooseOS = new JLabel("To start, choose your operating system installer you want to write to your USB:");
    static JLabel errorLabel = new JLabel("Error: ");
    static JLabel chooseMacOSLabel = new JLabel("Choose your macOS Version:");
    static JLabel linuxChoose = new JLabel("Please choose your linux Installer:");
    static JLabel linuxChoose2 = new JLabel("Please choose your linux Installer:");
    static JLabel linuxChoose3 = new JLabel("Please choose your linux Installer:");
    static JLabel linuxChoose4 = new JLabel("Please choose your linux Installer:");
    static JLabel othersChoose = new JLabel("Please choose an image file:");
    static JLabel progressLabel = new JLabel();
    static JLabel wallpaper = new JLabel();
    static JLabel welcomeLabel = new JLabel("Welcome!");
    static JLabel windowsChose = new JLabel("Choose your Windows Version");
    //Text fields
    static JTextField imageFileDirectory = new JTextField("Enter the file path or select a file..");
    static JTextArea jTextArea = new JTextArea();
    static JTextField usbDirectory = new JTextField("Enter or Choose the USB path..");
    static JTextField usbName = new JTextField("New USB name here..");
    //internal frame and it's components for End User License Agreement text for macOS
    static JInternalFrame jInternalFrame = new JInternalFrame();
    static JProgressBar progressBar = new JProgressBar(0, 100);
    static JScrollPane jScrollPane = new JScrollPane();
    static JScrollBar jScrollBar = new JScrollBar();
    //the menu bar and menus
    static JMenuBar menuBar = new JMenuBar();
    static JMenu fileMenu = new JMenu("File");
    static JMenu settingsMenu = new JMenu("Settings");
    static JMenu helpMenu = new JMenu("Help");
    //macOS Buttons
    static JButton macOS_13 = new JButton("macOS 13.0 (macOS Ventura)", new ImageIcon(Objects.requireNonNull(Definitions.class.getClassLoader().getResource("./src/resources/images/Ventura_Icons/Ventura_32x32x32.png"))));
    static JButton macOS_12 = new JButton("macOS 12.6 (Monterey)", new ImageIcon(Objects.requireNonNull(Definitions.class.getClassLoader().getResource("/images/Monterey_Icon/Monterey_32x32x32.png"))));
    static JButton macOS_11 = new JButton("macOS 11.6.8(Big Sur)", new ImageIcon("./src/resources/images/BigSur_Icons/BigSur_32x32x32.png"));
    static JButton macOS_1015 = new JButton("macOS 10.15 (Catalina)", new ImageIcon("./src/resources/images/Catalina_Icons/Catalina_32x32x32.png"));
    static JButton macOS_1014 = new JButton("macOS 10.14 (Mojave)", new ImageIcon("./src/resources/images/Mojave_Icons/Mojave_32x32x32.png"));
    static JButton macOS1013 = new JButton("macOS 10.13 (High Sierra)", new ImageIcon("./src/resources/images/HighSierra_Icons/HighSierra_32x32x32.png"));
    static JButton quitButton = new JButton("Quit");
    //Misc buttons
    static JButton selectFile = new JButton("Select a Image File");
    static JButton macOSButton = new JButton("macOS", new ImageIcon("./src/resources/images/Systems-Mac-Os-icon.png"));
    static JButton windowsButton = new JButton("Windows", new ImageIcon("./src/resources/images/windows-8-icon.png"));
    static JButton linuxButton = new JButton("Linux", new ImageIcon("./src/resources/images/OS-Linux-icon.png"));
    static JButton otherButton = new JButton("Other..");
    //Linux buttons
    static JButton archBasedDistros = new JButton("Arch Based Distributions", new ImageIcon("./src/resources/images/Linux_Icons/arch_round.png"));
    static JButton debianBasedDistros = new JButton("Debian Based Distributions", new ImageIcon("./src/resources/images/Linux_Icons/debian.png"));
    static JButton miscDistributions = new JButton("Miscellaneous Distributions", new ImageIcon("./src/resources/images/OS-Linux-icon.png"));
    static JButton arch = new JButton("Arch 2022.11.01 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/arch_round.png"));
    static JButton blackArch = new JButton("Black Arch Version 2021.09.1 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/blackarch.png"));
    static JButton blackArchMinimum = new JButton("Black Arch Minimum v2021.09.1 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/blackarch.png"));
    static JButton blackArchNet = new JButton("Black Arch Network Installer v2021.09.1 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/blackarch.png"));
    static JButton debian = new JButton("Debian 11-32 bit(Bullseye)", new ImageIcon("./src/resources/images/Linux_Icons/debian.png"));
    static JButton debian64 = new JButton("Debian 11 64-bit (Bullseye)", new ImageIcon("./src/resources/images/Linux_Icons/debian.png"));
    static JButton debianNet64 = new JButton("Debian 11 64-bit Network Installer(Bullseye)", new ImageIcon("./src/resources/images/Linux_Icons/debian.png"));
    static JButton debianNet = new JButton("Debian 11 32-bit Network Installer(Bullseye)", new ImageIcon("./src/resources/images/Linux_Icons/debian.png"));
    static JButton deepin = new JButton("Deepin OS 20.6", new ImageIcon("./src/resources/images/Linux_Icons/deepin.png"));
    static JButton elementaryOS = new JButton("Elementary OS Version 6.1", new ImageIcon("./src/resources/images/Linux_Icons/ElementaryOS.png"));
    static JButton fedoraWorkspace = new JButton("Fedora Workstation 36 32-bit", new ImageIcon("./src/resources/images/Linux_Icons/fedora.png"));
    static JButton fedoraWorkspace64 = new JButton("Fedora Workstation 36 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/fedora.png"));
    static JButton fedoraServer = new JButton("Fedora Server 36 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/fedora.png"));
    static JButton fedoraServer_ARM = new JButton("Fedora Server ARM 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/fedora.png"));
    static JButton fedoraServerNet64 = new JButton("Fedora Server Net 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/fedora.png"));
    static JButton fedoraServerNet_ARM = new JButton("Fedora Server Net ARM 64-bit", new ImageIcon("./src/resources/images/Linux_Icons/fedora.png"));
    static JButton linuxMintCinnamon = new JButton("Linux Mint Cinnamon Version 21", new ImageIcon("./src/resources/images/Linux_Icons/linux-mint.png"));
    static JButton linuxMintMate = new JButton("Linux Mint MATE Edition Version 21", new ImageIcon("./src/resources/images/Linux_Icons/linux-mint.png"));
    static JButton linuxMintXfce = new JButton("Linux Mint Xfce Edition Version 21", new ImageIcon("./src/resources/images/Linux_Icons/linux-mint.png"));
    static JButton manjaroGnome = new JButton("Manjaro Gnome Edition", new ImageIcon("./src/resources/images/Linux_Icons/manjaro.png"));
    static JButton manjaroXFCE = new JButton("Manjaro XFCE Edition", new ImageIcon("./src/resources/images/Linux_Icons/manjaro.png"));
    static JButton manjaroKDE = new JButton("Manjaro KDE Edition", new ImageIcon("./src/resources/images/Linux_Icons/manjaro.png"));
    static JButton solusMATE = new JButton("Solus MATE Edition Version 4.3", new ImageIcon("./src/resources/images/Linux_Icons/Solus.png"));
    static JButton solusBudgie = new JButton("Solus Budgie Edition Version 4.3", new ImageIcon("./src/resources/images/Linux_Icons/Solus.png"));
    static JButton solusGnome = new JButton("Solus Gnome Edition Version 4.3", new ImageIcon("./src/resources/images/Linux_Icons/Solus.png"));
    static JButton solusKDE = new JButton("Solus KDE Edition Version 4.3", new ImageIcon("./src/resources/images/Linux_Icons/Solus.png"));
    static JButton ubuntuServerLTS = new JButton("Ubuntu Server 22.04(Jammy Jellyfish)", new ImageIcon("./src/resources/images/Linux_Icons/ubuntu-round.png"));
    static JButton ubuntuLTS = new JButton("Ubuntu 22.04(Jammy Jellyfish)", new ImageIcon("./src/resources/images/Linux_Icons/ubuntu/original/ubuntu-aquamorphic2.png"));
    static JButton ubuntuServer = new JButton("Ubuntu Server 22.04 LTS(Jammy Jellyfish)", new ImageIcon("./src/resources/images/Linux_Icons/ubuntu-square.png"));
    static JButton ubuntu = new JButton("Ubuntu 22.10 (Kinetic Kudu)", new ImageIcon("./src/resources/images/Linux_Icons/ubuntu-round.png"));
    static JButton ubuntuUnity = new JButton(" Ubuntu Unity 22.10(Kinetic Kudu)", new ImageIcon("./src/resources/"));
    //Windows Buttons
    static JButton windows8 = new JButton("Windows 8.1 32-bit", new ImageIcon("./src/resources/images/Windows_Icons/win8_and_10.png"));
    static JButton windows8_64 = new JButton("Windows 8.1 64-bit", new ImageIcon("./src/resources/images/Windows_Icons/win8_and_10.png"));
    static JButton windows10 = new JButton("Windows 10 32-bit", new ImageIcon("./src/resources/images/Windows_Icons/win8_and_10.png"));
    static JButton windows10_64 = new JButton("Windows 10 64-bit", new ImageIcon("./src/resources/images/Windows_Icons/win8_and_10.png"));
    static JButton windows11 = new JButton("Windows 11", new ImageIcon("./src/resources/images/Windows_Icons/win11.png"));
    static JButton windowsXP = new JButton("Windows XP 32-bit", new ImageIcon("./src/resources/images/Windows_Icons/windows_xp-2.png"));
    static JButton windowsXP_64 = new JButton("Windows XP 64-bit ", new ImageIcon("./src/resources/images/Windows_Icons/windows_xp-2.png"));
    //(SubComment) Back and next buttons for the whole application
    static JButton back = new JButton("Back");
    static JButton back2 = new JButton("Back");
    static JButton back3 = new JButton("Back");
    static JButton back4 = new JButton("Back");
    static JButton back5 = new JButton("Back");
    static JButton back6 = new JButton("Back");
    static JButton back7 = new JButton("Back");
    static JButton back8 = new JButton("Back");
    static JButton back9 = new JButton("Back");
    static JButton next1 = new JButton("Next");
    static JButton next2 = new JButton("Next");
    static JButton selectUSB = new JButton("Select USB...");
    //Other buttons
    static JButton bootloaderNext = new JButton("Next");
    //Radio buttons
    static JRadioButton sysLinuxButton = new JRadioButton("Syslinux Bootloader");
    static JRadioButton cloverButton = new JRadioButton("Clover bootloader");
    static JRadioButton noneBootloaderOption = new JRadioButton("No bootloader(choose this if the image has a bootloader)");
    //File Chooser Windows for Choosing Images and USb Directories
    static JFileChooser chooseUSB = new JFileChooser();
    static JFileChooser isoImageChooser = new JFileChooser();
    static JCheckBox formatUSB = new JCheckBox("Fomat USB?");
    static JCheckBox installBootloaderOption = new JCheckBox("Install Bootloader?");
    static JLabel helpLabel = new JLabel("Stuck? More Info at ");
    //Menu Items
    static JMenuItem closeApplication = new JMenuItem("Close Application");
    static JLabel bootloaderOptionLabel = new JLabel("Choose your bootloader");
    static JMenuItem updateApplication = new JMenuItem("Check for updates");
    static JMenuItem settings = new JMenuItem("Settings");
    //static JMenuItem
    //Unspecific Declarations
    static String os = SoftwareInfo.getOS();
    static LinuxStorageDeviceUnmounter linuxUnmounter = new LinuxStorageDeviceUnmounter();
    static OSXStorageDeviceUnmounter osxUnmounter = new OSXStorageDeviceUnmounter();
    static WindowsStorageDeviceUnmounter winUnmounter = new WindowsStorageDeviceUnmounter();
    static USBDeviceDetectorManager usbDetectorManager = new USBDeviceDetectorManager();
    static Desktop desktopActions = Desktop.getDesktop();
    static ArrayList<Image> icons = new ArrayList();
    private static byte osToWrite = 0;
    private static byte bootloaderToWrite = 0;

    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    public static void initializeDefinitions() {
        if (System.getProperty("os.version").charAt(1) != '0') {
            icons.add(new ImageIcon(Constants.appData + "./src/resources/images/app_icons/ico_macOS11/icon_16x16.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_16x16@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_32x32.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_32x32@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_64x64.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_64x64@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_128x64.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_128x128@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_256x256.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS11/icon_256x256@2x.png").getImage());
        }
        if (System.getProperty("os.version").charAt(1) == '0') {
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_16x16.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_16x16@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_32x32.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_32x32@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_64x64.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_64x64@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_128x64.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_128x128@2x.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_256x256.png").getImage());
            icons.add(new ImageIcon("./src/resources/images/app_icons/ico_macOS1015/icon_256x256@2x.png").getImage());
        }
    }

    public CardLayout getLayout() {
        return layout;
    }

    public JPanel getApplicationPanel() {
        return applicationPanel;
    }


    public JPanel getFinishedScreen() {
        return finishedScreen;
    }

    public JPanel getLinuxPane() {
        return linuxPane;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getMacOSPane() {
        return macOSPane;
    }

    public JPanel getMacOSEULA() {
        return macOSEULA;
    }

    public JPanel getOthersPane() {
        return othersPane;
    }

    public JPanel getWelcomePane() {
        return welcomePane;
    }

    public JPanel getWindowsPane() {
        return windowsPane;
    }

    public JPanel getChooseBootloaderPane() {
        return chooseBootloaderPane;
    }

    public JPanel getWriteImageToUSB() {
        return progressScreen;
    }

    public JPanel getDebianPane() {
        return debianPane;
    }

    public JPanel getArchPane() {
        return archPane;
    }

    public JPanel getMiscLinuxPane() {
        return miscLinuxPane;
    }

    public JLabel getSupportedFileTypes() {
        return supportedFileTypes;
    }

    public JLabel getChooseOS() {
        return chooseOS;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JLabel getChooseMacOSLabel() {
        return chooseMacOSLabel;
    }

    public JLabel getLinuxChoose() {
        return linuxChoose;
    }

    public JLabel getOthersChoose() {
        return othersChoose;
    }

    public JLabel getProgressLabel() {
        return progressLabel;
    }

    public JLabel getWallpaper() {
        return wallpaper;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public JLabel getWindowsChose() {
        return windowsChose;
    }

    public JTextField getImageFileDirectory() {
        return imageFileDirectory;
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public JTextField getUsbDirectory() {
        return usbDirectory;
    }

    public JInternalFrame getjInternalFrame() {
        return jInternalFrame;
    }

    public JProgressBar getjProgressBar() {
        return progressBar;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public JScrollBar getjScrollBar() {
        return jScrollBar;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JMenu getSettingsMenu() {
        return settingsMenu;
    }

    public JMenu getHelpMenu() {
        return helpMenu;
    }

    public JButton getMacOS_13() {
        return macOS_13;
    }

    public JButton getMacOS_12() {
        return macOS_12;
    }

    public JButton getMacOS_11() {
        return macOS_11;
    }

    public JButton getMacOS_1015() {
        return macOS_1015;
    }

    public JButton getMacOS_1014() {
        return macOS_1014;
    }

    public JButton getMacOS1013() {
        return macOS1013;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JButton getSelectFile() {
        return selectFile;
    }

    public JButton getMacOSButton() {
        return macOSButton;
    }

    public JButton getWindowsButton() {
        return windowsButton;
    }

    public JButton getLinuxButton() {
        return linuxButton;
    }

    public JButton getOtherButton() {
        return otherButton;
    }

    public JButton getArch() {
        return arch;
    }

    public JButton getBlackArch() {
        return blackArch;
    }

    public JButton getBlackArchMinimum() {
        return blackArchMinimum;
    }

    public JButton getBlackArchNet() {
        return blackArchNet;
    }

    public JButton getDebian() {
        return debian;
    }

    public JButton getDebian64() {
        return debian64;
    }

    public JButton getDebianNet64() {
        return debianNet64;
    }

    public JButton getDebianNet() {
        return debianNet;
    }

    public JButton getDeepin() {
        return deepin;
    }

    public JButton getElementaryOS() {
        return elementaryOS;
    }

    public JButton getFedoraWorkspace() {
        return fedoraWorkspace;
    }

    public JButton getFedoraWorkspace64() {
        return fedoraWorkspace64;
    }

    public JButton getFedoraServer() {
        return fedoraServer;
    }

    public JButton getFedoraServer_ARM() {
        return fedoraServer_ARM;
    }

    public JButton getFedoraServerNet64() {
        return fedoraServerNet64;
    }

    public JButton getFedoraServerNet_ARM() {
        return fedoraServerNet_ARM;
    }

    public JButton getLinuxMintCinnamon() {
        return linuxMintCinnamon;
    }

    public JButton getLinuxMintMate() {
        return linuxMintMate;
    }

    public JButton getLinuxMintXfce() {
        return linuxMintXfce;
    }

    public JButton getManjaroGnome() {
        return manjaroGnome;
    }

    public JButton getManjaroXFCE() {
        return manjaroXFCE;
    }

    public JButton getManjaroKDE() {
        return manjaroKDE;
    }

    public JButton getSolusMATE() {
        return solusMATE;
    }

    public JButton getSolusBudgie() {
        return solusBudgie;
    }

    public JButton getSolusGnome() {
        return solusGnome;
    }

    public JButton getSolusKDE() {
        return solusKDE;
    }

    public JButton getUbuntuServerLTS() {
        return ubuntuServerLTS;
    }

    public JButton getUbuntuLTS() {
        return ubuntuLTS;
    }

    public JButton getUbuntuServer() {
        return ubuntuServer;
    }

    public JButton getUbuntu() {
        return ubuntu;
    }

    public JButton getWindows8() {
        return windows8;
    }

    public JButton getWindows8_64() {
        return windows8_64;
    }

    public JButton getWindows10() {
        return windows10;
    }

    public JButton getWindows10_64() {
        return windows10_64;
    }

    public JButton getWindows11() {
        return windows11;
    }

    public JButton getWindowsXP() {
        return windowsXP;
    }

    public JButton getWindowsXP_64() {
        return windowsXP_64;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getBack2() {
        return back2;
    }

    public JButton getBack3() {
        return back3;
    }

    public JButton getBack4() {
        return back4;
    }

    public JButton getBack5() {
        return back5;
    }

    public JButton getBack6() {
        return back6;
    }

    public JButton getBack7() {
        return back7;
    }

    public JButton getBack8() {
        return back8;
    }

    public JButton getNext() {
        return next1;
    }

    public JButton getProceed() {
        return next2;
    }

    public JButton getSelectUSB() {
        return selectUSB;
    }

    public JButton getBootloaderNext() {
        return bootloaderNext;
    }

    public JRadioButton getSysLinuxButton() {
        return sysLinuxButton;
    }

    public JRadioButton getCloverButton() {
        return cloverButton;
    }

    public JRadioButton getNoneBootloaderOption() {
        return noneBootloaderOption;
    }

    public JFileChooser getChooseUSB() {
        return chooseUSB;
    }

    public JFileChooser getIsoImageChooser() {
        return isoImageChooser;
    }

    public JMenuItem getCloseApplication() {
        return closeApplication;
    }

    public JLabel getBootloaderOptionLabel() {
        return bootloaderOptionLabel;
    }

    public JMenuItem getUpdateApplication() {
        return updateApplication;
    }

    public String getOs() {
        return os;
    }

    public LinuxStorageDeviceUnmounter getLinuxUnmounter() {
        return linuxUnmounter;
    }

    public OSXStorageDeviceUnmounter getOsxUnmounter() {
        return osxUnmounter;
    }

    public WindowsStorageDeviceUnmounter getWinUnmounter() {
        return winUnmounter;
    }

    public USBDeviceDetectorManager getUsbDetectorManager() {
        return usbDetectorManager;
    }

    public Desktop getDesktopActions() {
        return desktopActions;
    }
}
