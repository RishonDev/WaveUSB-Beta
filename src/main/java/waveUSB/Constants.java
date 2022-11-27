package waveUSB;

public interface Constants {
    String macOS11 = "https://swcdn.apple.com/content/downloads/00/52/012-38280-A_U42U8QWY6W/xlqy3umkvwvbsjyh471ips53ux8r0jdywq/InstallAssistant.pkg";
    String macOS12 = "https://swcdn.apple.com/content/downloads/03/23/012-40494-A_LYJ5GAY6QK/8iswu187h4yn4os8rq1gzt2ey1aasgemsw/InstallAssistant.pkg";
    String macOS13 = "https://swcdn.apple.com/content/downloads/22/62/012-84573-A_0XT3CECBH7/5x8w3dc7kezc43m4dzpvs8qnx8uoy2qatr/InstallAssistant.pkg";
    String ubuntu = "https://releases.ubuntu.com/22.04/ubuntu-22.04.1-desktop-amd64.iso";
    String debian64 = "https://cdimage.debian.org/debian-cd/current/amd64/bt-dvd/debian-11.4.0-amd64-DVD-1.iso.torrent";
    String debian = "https://cdimage.debian.org/debian-cd/current/i386/bt-dvd/debian-11.4.0-i386-DVD-1.iso.torrent";
    String debianNet64 = "https://cdimage.debian.org/debian-cd/current/amd64/iso-cd/debian-11.4.0-amd64-netinst.iso";
    String debianNet = "https://cdimage.debian.org/debian-cd/current/i386/iso-cd/debian-11.4.0-i386-netinst.iso";
    String ubuntuServer = "https://releases.ubuntu.com/22.04/ubuntu-22.04.1-live-server-amd64.iso";
    String arch = "http://mirrors.edge.kernel.org/archlinux/iso/2022.11.01/archlinux-2022.11.01-x86_64.iso";
    String fedoraWorkspace64 = "https://download.fedoraproject.org/pub/fedora/linux/releases/36/Workstation/x86_64/iso/Fedora-Workstation-Live-x86_64-36-1.5.iso";
    String fedoraWorkspace = "https://download.fedoraproject.org/pub/fedora/linux/releases/36/Workstation/aarch64/iso/Fedora-Workstation-Live-aarch64-36-1.5.iso";
    String fedoraServer = "https://download.fedoraproject.org/pub/fedora/linux/releases/36/Server/x86_64/iso/Fedora-Server-dvd-x86_64-36-1.5.iso";
    String fedoraServer_ARM = "https://download.fedoraproject.org/pub/fedora/linux/releases/36/Server/aarch64/iso/Fedora-Server-dvd-aarch64-36-1.5.iso";
    String deepin = "https://cdimage.deepin.com/releases/20.6/deepin-desktop-community-20.6-amd64.iso";
    String manjaroGnome = "https://download.manjaro.org/gnome/21.3.4/manjaro-gnome-21.3.4-220718-linux515.iso";
    String manjaroXFCE = "https://download.manjaro.org/xfce/21.3.4/manjaro-xfce-21.3.4-220718-linux515.iso";
    String manjaroKDE = "https://download.manjaro.org/kde/21.3.4/manjaro-kde-21.3.4-220718-linux515.iso";
    String linuxMintXFCE = "https://mirrors.kernel.org/linuxmint/stable/21/linuxmint-21-xfce-64bit.iso";
    String linuxMintCinnamon = "https://mirrors.kernel.org/linuxmint/stable/21/linuxmint-21-cinnamon-64bit.iso";
    String linuxMintMate = "https://mirrors.kernel.org/linuxmint/stable/21/linuxmint-21-mate-64bit.iso";
    String elementaryOS = "https://sgp1.dl.elementary.io/download/MTY1ODIyMTYzMg==/elementaryos-6.1-stable.20211218-rc.iso";
    String solusGnome = "https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-GNOME.iso";
    String solusBudgie = "https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-Budgie.iso";
    String solusMATE = "https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-MATE.iso";
    String solusKDE = "https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-Plasma.iso";
    String blackArch = "https://ftp.halifax.rwth-aachen.de/blackarch/iso/blackarch-linux-full-2021.09.01-x86_64.iso";
    String blackArchMinimum = "https://ftp.halifax.rwth-aachen.de/blackarch/iso/blackarch-linux-slim-2021.09.01-x86_64.iso";
    String blackArchNet = "https://ftp.halifax.rwth-aachen.de/blackarch/iso/blackarch-linux-netinst-2021.09.01-x86_64.iso";
    //not in CLI
    String fedoraServerNet_ARM = "https://mirrors.tuna.tsinghua.edu.cn/fedora/releases/36/Server/aarch64/iso/Fedora-Server-netinst-aarch64-36-1.5.iso";
    String fedoraServerNet = "https://download.fedoraproject.org/pub/fedora/linux/releases/36/Server/x86_64/iso/Fedora-Server-netinst-x86_64-36-1.5.iso";
    //path for the application Data directory
    String appData = System.getProperty("user.home") + "/.WaveUSB/";
    //Download link for the application binaries
    //Download via GitHub releases
    String macOS10_15 = "https://github.com/JavaOpenSoft/WaveUSB/releases/download/macOS10.15.7/Catalina.iso";
    String macOS10_14 = "https://github.com/JavaOpenSoft/WaveUSB/releases/download/macOS10.14.6/Mojave.iso";
    String macOS10_13 = "https://github.com/JavaOpenSoft/WaveUSB/releases/download/macOS10.13.6/HighSierra.iso";
    String windowsXP64 = "https://github.com/JavaOpenSoft/WaveUSB.old/releases/download/WindowsXP64/Windows.XP.Professional.64-bit.Corporate.Edition.CD.Key.VCFQD-V9FX9-46WVH-K3CD4-4J3JM.iso";
    String WindowsXP = "https://github.com/JavaOpenSoft/WaveUSB/releases/download/WindowsXP/WindowsXP-32bit-Professional-ServicePack3.iso";
    //browse to downlaod image
    String Windows8 = "https://www.microsoft.com/en-in/software-download/windows8ISO";
    String Windows10 = "https://www.microsoft.com/en-in/software-download/windows10ISO";
    String Windows11 = "https://www.microsoft.com/en-in/software-download/windows11";
    //No Archives images are available so commented out to avoid taking up unnecessary memory
    //String Windows12 = null;

    String createUSBmacOS1013 = "sudo /Applications/Install\\ macOS\\ High\\ Sierra.app/Contents/Resources/createinstallmedia --volume";
    String createUSBmacOS1014 = "sudo /Applications/Install\\ macOS\\ Mojave.app/Contents/Resources/createinstallmedia --volume";
    String createUSBmacOS1015 = "createUSB_macOS1014";
    String createUSBmacOS11 = "sudo /Applications/Install\\ macOS\\ Big\\ Sur.app/Contents/Resources/createinstallmedia --volume";
    String createUSBmacOS12 = "sudo /Applications/Install\\ macOS\\ Monterey.app/Contents/Resources/createinstallmedia --volume";
    String createUSBmacOS13 = "sudo /Applications/Install\\ macOS\\ Ventura.app/Contents/Resources/createinstallmedia --volume /Volumes/MyVolume\n";
    String ubuntu2210 = "";
    String ubuntu2210Server = "";
    String ubuntu2204T2 = "";
    String ubuntu2204T2SafeGraphics = "";
    String ubuntu2204T2ExternalParameters = "";
    String puppyLinux = "";
    String T2Fedora = "";
    String T2Gentoo = "";
    String T2EndevourOS = "";
    //String

}
