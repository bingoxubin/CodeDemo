package com.bingoabin.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author bingoabin
 * @date 2022/4/14 16:45
 */
public class TestPng {
	//图片拷贝  二进制
	public static void main(String[] args) {
		TestPng pngTest = new TestPng();
		pngTest.copyFile("E:\\60.test\\test6.txt","E:\\60.test\\test7.txt");
	}

	public void copyFile(String srcPath, String destPath) {
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		FileOutputStream fileOutputStream = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(srcFile);
			fileOutputStream = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fileInputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
