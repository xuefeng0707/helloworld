package io.echo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class IoEchoClient {
	
	private static final Object LOCK = new Object();
	
	private static String line = null;
	
	public static void main(String[] args) throws IOException {
		new Thread(new Worker()).start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			synchronized (LOCK) {
				while(line != null) {
					try {
						LOCK.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				try {
					if((line = reader.readLine()) != null) {
						LOCK.notifyAll();
					} else {
						line = "quit";
						LOCK.notifyAll();
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private static class Worker implements Runnable {

		public void run() {
			Socket sock = new Socket();
			try {
				sock.connect(new InetSocketAddress(9999), 30);
				InputStream in = sock.getInputStream();
				OutputStream out = sock.getOutputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while(true) {
					synchronized (LOCK) {
						while(line == null) {
							try {
								LOCK.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						if("quit".equals(line)) {
							sock.close();
							break;
						}
						
						out.write(line.getBytes());
						System.out.println("Sent to server: " + line);
						
						while((len = in.read(buffer)) != -1) {
							baos.write(buffer, 0, len);
						}
						
						System.out.println("Received from server: " + baos.toString());
						
						line = null;
						LOCK.notifyAll();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
