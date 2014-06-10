package classloader;

import java.net.URL;

import sun.misc.Launcher;

public class ClassLoaderTest {

	public static void main(String[] args) {
		
		URL[] bootUrls = Launcher.getBootstrapClassPath().getURLs();
		System.out.println("Bootstrap classloader class path:");
		for (URL url : bootUrls) {
			System.out.println("    " + url.toExternalForm());
		}
		System.out.println(System.getProperty("sun.boot.class.path"));
		
		printClassLoaderHierarchy(Thread.currentThread().getContextClassLoader());
	}

	public static void printClassLoaderHierarchy(ClassLoader cl) {
		while(cl != null) {
			System.out.println(cl);
			cl = cl.getParent();
		}
	}
}
