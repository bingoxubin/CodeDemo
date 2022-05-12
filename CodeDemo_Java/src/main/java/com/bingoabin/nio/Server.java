package com.bingoabin.nio;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author bingoabin
 * @date 2022/4/14 17:02
 */
public class Server {
	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(9090);
		Socket socket = serverSocket.accept();
		InputStream is = socket.getInputStream();
		FileOutputStream fos = new FileOutputStream("E:\\60.test\\test8.txt");

		byte[] bytes = new byte[1024];
		int len;
		while ((len = is.read(bytes)) != -1) {
			fos.write(bytes, 0, len);
		}
		System.out.println("发送成功！");
		//向客户端回一句话
		OutputStream os = socket.getOutputStream();
		os.write("收到".getBytes());

		fos.close();
		is.close();
		socket.close();
		serverSocket.close();
		os.close();
	}
}
