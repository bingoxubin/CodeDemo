package com.bingoabin.nio;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author bingoabin
 * @date 2022/4/14 17:02
 */
public class Client {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket(InetAddress.getByName("localhost"),9090);
		OutputStream os = socket.getOutputStream();
		FileInputStream fis = new FileInputStream("E:\\60.test\\test7.txt");

		byte[] bytes = new byte[1024];
		int len;
		while ((len = fis.read(bytes)) != -1) {
			os.write(bytes, 0, len);
		}
		socket.shutdownOutput();

		InputStream is = socket.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int size;
		while ((size = is.read(buffer)) != -1) {
			baos.write(buffer,0,size);
		}
		System.out.println(baos.toString());

		fis.close();
		os.close();
		socket.close();
		is.close();
		baos.close();
	}
}
