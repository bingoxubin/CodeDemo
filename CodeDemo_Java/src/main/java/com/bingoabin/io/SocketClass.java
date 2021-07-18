package com.bingoabin.io;

import org.junit.Test;

import java.io.*;
import java.net.*;

/**
 * @author xubin03
 * @date 2021/5/19 8:53 下午
 */
public class SocketClass {
	//TCP编程  客户端  服务端
	@Test
	public void client() throws IOException {
		Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
		OutputStream os = socket.getOutputStream();
		FileInputStream fis = new FileInputStream(new File("beauty.jpg"));
		byte[] buffer = new byte[1024];
		int len;
		while ((len = fis.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		socket.shutdownOutput();

		InputStream is = socket.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bufferr = new byte[20];
		int len1;
		while ((len1 = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len1);
		}
		System.out.println(baos.toString());

		fis.close();
		os.close();
		socket.close();
		baos.close();
	}

	@Test
	public void server() throws IOException {
		ServerSocket ss = new ServerSocket(9090);
		Socket socket = ss.accept();
		InputStream is = socket.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File("beauty2.jpg"));
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
		}
		System.out.println("图片传输完成");

		OutputStream os = socket.getOutputStream();
		os.write("你好，美女，照片我已收到，非常漂亮！".getBytes());

		fos.close();
		is.close();
		socket.close();
		ss.close();
		os.close();
	}

	//UDP编程   发送端  接收端
	@Test
	public void sender() throws IOException {
		DatagramSocket socket = new DatagramSocket();

		String str = "我是UDP方式发送的导弹";
		byte[] data = str.getBytes();
		InetAddress inet = InetAddress.getLocalHost();
		DatagramPacket packet = new DatagramPacket(data, 0, data.length, inet, 9090);
		socket.send(packet);

		socket.close();
	}

	@Test
	public void receiver() throws IOException {
		DatagramSocket socket = new DatagramSocket(9090);

		byte[] buffer = new byte[100];
		DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

		socket.receive(packet);
		System.out.println(new String(packet.getData(), 0, packet.getLength()));

		socket.close();
	}

	//URL编程
	public static void main(String[] args) {
		HttpURLConnection urlConnection = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL("http://localhost:8080/examples/beauty.jpg");
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();

			is = urlConnection.getInputStream();
			fos = new FileOutputStream("day10\\beauty3.jpg");

			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			System.out.println("下载完成");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
	}
}
