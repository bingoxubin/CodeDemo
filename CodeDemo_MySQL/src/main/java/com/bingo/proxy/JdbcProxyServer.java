package com.bingo.proxy;

import java.io.*;
import java.net.*;

/**
 * @author bingoabin
 * @date 2023/5/26 14:58
 * @Description:
 */
public class JdbcProxyServer {
	private static final int PROXY_PORT = 20001; // 代理服务器监听的端口号
	private static final String REAL_JDBC_HOST = "localhost"; // 真实的 JDBC 地址
	private static final int REAL_JDBC_PORT = 3306; // 真实的 JDBC 端口号

	public static void main(String[] args) {
		try {
			// 创建代理服务器的 ServerSocket
			ServerSocket serverSocket = new ServerSocket(PROXY_PORT);
			System.out.println("代理服务器已启动，监听端口: " + PROXY_PORT);

			while (true) {
				// 接受客户端连接请求
				Socket clientSocket = serverSocket.accept();
				System.out.println("接收到新的连接请求: " + clientSocket.getInetAddress().getHostAddress());

				// 创建代理线程处理连接请求
				ProxyThread proxyThread = new ProxyThread(clientSocket);
				proxyThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ProxyThread extends Thread {
		private Socket clientSocket;

		public ProxyThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				// 获取客户端请求的主机名和端口号
				String requestHost = clientSocket.getInetAddress().getHostAddress();
				int requestPort = clientSocket.getPort();
				System.out.println("请求的主机名和端口号: " + requestHost + ":" + requestPort);

				// 替换主机名和端口号为真实的 JDBC 地址
				Socket jdbcSocket = new Socket(REAL_JDBC_HOST, REAL_JDBC_PORT);

				// 创建客户端与 JDBC 服务器之间的数据传输线程
				Thread clientToJdbcThread = new DataTransferThread(clientSocket.getInputStream(), jdbcSocket.getOutputStream());
				clientToJdbcThread.start();

				// 创建 JDBC 服务器与客户端之间的数据传输线程
				Thread jdbcToClientThread = new DataTransferThread(jdbcSocket.getInputStream(), clientSocket.getOutputStream());
				jdbcToClientThread.start();

				// 等待数据传输线程完成
				clientToJdbcThread.join();
				jdbcToClientThread.join();

				// 关闭连接
				jdbcSocket.close();
				clientSocket.close();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class DataTransferThread extends Thread {
		private InputStream inputStream;
		private OutputStream outputStream;

		public DataTransferThread(InputStream inputStream, OutputStream outputStream) {
			this.inputStream = inputStream;
			this.outputStream = outputStream;
		}

		@Override
		public void run() {
			try {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
					outputStream.flush();

					String data = new String(buffer, 0, bytesRead, "UTF-8");
					System.out.println(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
