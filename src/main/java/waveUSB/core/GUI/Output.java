package waveUSB.core.GUI;

import java.net.URLClassLoader;
import java.net.URL;

public class Output {
    public static void main(String[] args) {
        URL[] urls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
    }
}