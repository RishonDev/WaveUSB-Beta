package javaPack.waveUSB;

public interface Constants {
    String macOS11="https://swcdn.apple.com/content/downloads/44/35/012-22320-A_AOID136T4U/g33x1akyjzjpkpe7z0xn7nguoakljpe3a8/InstallAssistant.pkg";
    String macOS12="https://swcdn.apple.com/content/downloads/16/08/012-06873-A_636SHHRD4L/528ojpmw00mulgfjsz9k50modkj31a9v0p/InstallAssistant.pkg";
    String macOS12_MBP="https://swcdn.apple.com/content/downloads/16/08/012-06873-A_636SHHRD4L/528ojpmw00mulgfjsz9k50modkj31a9v0p/InstallAssistant.pkg";
    String ubuntu="https://releases.ubuntu.com/22.04/ubuntu-22.04-desktop-amd64.iso?_ga=2.53172328.1937538139.1658218589-1552335834.1657779817";
    String debian64="https://cdimage.debian.org/debian-cd/current/amd64/bt-dvd/debian-11.4.0-amd64-DVD-1.iso.torrent";
    String debian="https://cdimage.debian.org/debian-cd/current/i386/bt-dvd/debian-11.4.0-i386-DVD-1.iso.torrent";
    String debianNet64="https://cdimage.debian.org/debian-cd/current/amd64/iso-cd/debian-11.4.0-amd64-netinst.iso";
    String debianNet="https://cdimage.debian.org/debian-cd/current/i386/iso-cd/debian-11.4.0-i386-netinst.iso";
    String ubuntuServer="https://releases.ubuntu.com/22.04/ubuntu-22.04-live-server-amd64.iso";
    String arch="http://il.us.mirror.archlinux-br.org/iso/2022.07.01/archlinux-2022.07.01-x86_64.iso";
    String fedoraWorkspace64="https://download.fedoraproject.org/pub/fedora/linux/releases/36/Workstation/x86_64/iso/Fedora-Workstation-Live-x86_64-36-1.5.iso";
    String fedoraWorkspace="https://download.fedoraproject.org/pub/fedora/linux/releases/36/Workstation/aarch64/iso/Fedora-Workstation-Live-aarch64-36-1.5.iso";
    String fedoraServer="https://download.fedoraproject.org/pub/fedora/linux/releases/36/Server/x86_64/iso/Fedora-Server-dvd-x86_64-36-1.5.iso";
    String fedoraServer_ARM="https://download.fedoraproject.org/pub/fedora/linux/releases/36/Server/aarch64/iso/Fedora-Server-dvd-aarch64-36-1.5.iso";
    String deepin="https://cdimage.deepin.com/releases/20.6/deepin-desktop-community-20.6-amd64.iso";
    String manjaroGnome="https://download.manjaro.org/gnome/21.3.4/manjaro-gnome-21.3.4-220718-linux515.iso";
    String manjaroXFCE="https://download.manjaro.org/xfce/21.3.4/manjaro-xfce-21.3.4-220718-linux515.iso";
    String manjaroKDE="https://download.manjaro.org/kde/21.3.4/manjaro-kde-21.3.4-220718-linux515.iso";
    String linuxMintEdge="https://mirrors.kernel.org/linuxmint/stable/20.3/linuxmint-20.3-cinnamon-64bit-edge.iso";
    String linuxMintXFCE="https://mirrors.kernel.org/linuxmint/stable/20.3/linuxmint-20.3-xfce-64bit.iso";
    String linuxMintCinnamon="https://mirrors.kernel.org/linuxmint/stable/20.3/linuxmint-20.3-cinnamon-64bit.iso";
    String linuxMintMate="https://mirrors.kernel.org/linuxmint/stable/20.3/linuxmint-20.3-mate-64bit.iso";
    String elementaryOS="https://sgp1.dl.elementary.io/download/MTY1ODIyMTYzMg==/elementaryos-6.1-stable.20211218-rc.iso";
    String solusGnome="https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-GNOME.iso";
    String solusBudgie="https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-Budgie.iso";
    String solusMATE="https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-MATE.iso";
    String solusKDE="https://mirrors.rit.edu/solus/images/4.3/Solus-4.3-Plasma.iso";
    String blackArch="https://ftp.halifax.rwth-aachen.de/blackarch/iso/blackarch-linux-full-2021.09.01-x86_64.iso";
    String blackArchMinimum="https://ftp.halifax.rwth-aachen.de/blackarch/iso/blackarch-linux-slim-2021.09.01-x86_64.iso";
    String blackArchNet="https://ftp.halifax.rwth-aachen.de/blackarch/iso/blackarch-linux-netinst-2021.09.01-x86_64.iso";
    //not in CLI
    String fedoraServerNet_ARM = "https://mirrors.tuna.tsinghua.edu.cn/fedora/releases/36/Server/aarch64/iso/Fedora-Server-netinst-aarch64-36-1.5.iso";
    String fedoraServerNet = "https://download.fedoraproject.org/pub/fedora/linux/releases/36/Server/x86_64/iso/Fedora-Server-netinst-x86_64-36-1.5.iso";
    //path for the application Data directory
    String appDatamacOS = "//Library//Application\\ Support//WaveUSB/";
    String appDataLinux = System.getProperty("user.home")+"/.WaveUSB/";
    String appDataWindows = System.getProperty("user.home")+"/.WaveUSB/";
}
