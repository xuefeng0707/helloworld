package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	
	private static int i=0;
	
	private static AtomicInteger ai = new AtomicInteger(0);

	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
			new TestThread().start();
		}
	}

	private static class TestThread extends Thread {
		@Override
		public void run() {
			System.out.println((++i) + "  " + ai.incrementAndGet());
		}
	}
}
