package waveUSB.core;

@SuppressWarnings("ALL")
public class SoftwareInfo {
    private static final String buildMode = "Staging";
    private static final String About = "GUI Edition of WaveUSB, A complete USB writer designed in Java And Shell(Batch scripts if on  Windows). ";
    private static final String versionNumber = "0.8";

    public static String getVersionNumber() {
        return versionNumber;
    }

    public static String getVersion() {
        return versionNumber + "-" + buildMode;
    }

    public static String getAbout() {
        return About;
    }

    public static String getBuildMode() {
        return buildMode;
    }

    public static String getOS() {
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows"))
            return "Windows";
        else if (osName.equals("Mac OS X"))
            return System.getProperty("os.name");
        else return "Linux";
    }

    public static String getOSVersion() {
        return System.getProperty("os.version");
    }

    public static String getHomeDirectory() {
        return System.getProperty("user.home");
    }

    public static String getAppDataDirectory() {
        return getHomeDirectory() + "/.WaveUSB";
    }

}
