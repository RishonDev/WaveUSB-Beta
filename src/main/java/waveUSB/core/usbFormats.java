package waveUSB.core;

public class usbFormats {
    private String os = SoftwareInfo.getOS();

    public String HFSPlus() {
        if (os.equals("Windows")) return "";
        else if (os.equals("Mac")) return "JHFS+";
        else if (os.equals("Linux")) return "hfs+";
        return null;
    }

    public String FAT32() {
        return "fat32";
    }

    public String EXT4() {
        return "ext4";
    }

    public String Btrfs() {
        return "btrfs";
    }

    public String exFAT() {
        return "exfat";
    }

    public String NTFS() {
        return "";
    }
}
