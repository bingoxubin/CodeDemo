package com.bingo.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author bingoabin
 * @date 2023/5/25 14:29
 * @Description:
 */
public class NginxTest {
	public static void main(String[] args) {
		InetAddress sourceHost = null;
		int sourcePort = 20001;
		String targetHost = "localhost";
		int targetPort = 3306;

		try {
			sourceHost = InetAddress.getByName("hahaha");
			ServerSocket serverSocket = new ServerSocket(sourcePort, 0, sourceHost);
			System.out.println("代理服务器已启动，监听端口：" + sourcePort);

			while (true) {
				Socket sourceSocket = serverSocket.accept();
				System.out.println("接收到源地址连接：" + sourceSocket.getInetAddress() + ":" + sourceSocket.getPort());

				Socket targetSocket = new Socket(targetHost, targetPort);
				System.out.println("连接到目标地址：" + targetHost + ":" + targetPort);

				ProxyThread sourceToTargetThread = new ProxyThread(sourceSocket.getInputStream(), targetSocket.getOutputStream());
				ProxyThread targetToSourceThread = new ProxyThread(targetSocket.getInputStream(), sourceSocket.getOutputStream());

				sourceToTargetThread.start();
				targetToSourceThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ProxyThread extends Thread {
		private final InputStream input;
		private final OutputStream output;

		public ProxyThread(InputStream input, OutputStream output) {
			this.input = input;
			this.output = output;
		}

		@Override
		public void run() {
			byte[] buffer = new byte[4096];
			int bytesRead;

			try {
				while ((bytesRead = input.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
					output.flush();
					System.out.println(new String(buffer, 0, bytesRead));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
