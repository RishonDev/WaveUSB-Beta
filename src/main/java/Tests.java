import java.io.File;

public class Tests {
    public static void main(String[] args) {
        File diskPartition = new File("/Volumes/Macintosh HD");
        long totalCapacity = diskPartition.getTotalSpace();

        double freePartitionSpace = diskPartition.getFreeSpace();
        double usablePatitionSpace = diskPartition.getUsableSpace();

        System.out.println("**** Sizes in Mega Bytes ****\n");

        System.out.println("Total C partition size : " + totalCapacity / (1024 * 1024) + " MB");
        System.out.println("Usable Space : " + usablePatitionSpace / (1024 * 1024) + " MB");
        System.out.println("Free Space : " + freePartitionSpace / (1024 * 1024) + " MB");

        System.out.println("\n**** Sizes in Giga Bytes ****\n");

        System.out.println("Total C partition size : " + totalCapacity / (1024 * 1024 * 1024) + " GB");
        System.out.println("Usable Space : " + usablePatitionSpace / (1024 * 1024 * 1024) + " GB");
        System.out.println("Free Space : " + freePartitionSpace / (1024 * 1024 * 1024) + " GB");

        System.out.println(System.getProperty("os.version"));
    }
}
