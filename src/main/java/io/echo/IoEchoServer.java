package io.echo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IoEchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket srvSock = new ServerSocket();
		srvSock.bind(new InetSocketAddress(9999), 5);
		
		Executor executor = Executors.newCachedThreadPool();
		
		Socket sock;
		while((sock = srvSock.accept()) != null) {
//			sock.set
			executor.execute(new Worker(sock));
		}
	}
	
	private static class Worker implements Runnable {
		
		private Socket sock;
		
		public Worker(Socket sock) {
			this.sock = sock;
		}

		public void run() {
			try {
				InputStream in = sock.getInputStream();
				OutputStream out = sock.getOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while(true) {
					while((len = in.read(buffer)) != -1) {
						baos.write(buffer, 0, len);
					}
					
					out.write(baos.toByteArray());
					baos.reset();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
