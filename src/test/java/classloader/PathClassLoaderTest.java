package classloader;


// -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:c:/gc.log
public class PathClassLoaderTest {
	
	public static void main(String[] args) throws ClassNotFoundException {
		while(true) {
			PathClassLoader cl = new PathClassLoader("temp");
			Class<?> clazz = cl.loadClass("classloader.TestClass");
			
			PathClassLoader cl2 = new PathClassLoader("temp");
			Class<?> clazz2 = cl2.loadClass("classloader.TestClass");
			System.out.println(clazz == clazz2);
		}
	}
	
}
