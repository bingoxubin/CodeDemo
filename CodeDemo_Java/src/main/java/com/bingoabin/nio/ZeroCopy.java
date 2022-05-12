package com.bingoabin.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author bingoabin
 * @date 2022/4/16 16:40
 */
public class ZeroCopy {
	public static void main(String[] args) throws Exception{
		long startTime = System.currentTimeMillis();
		File srcFile = new File("E:\\60.test\\test6.txt");
		File destFile = new File("E:\\60.test\\test9.txt");

		FileChannel srcFileChannel = new RandomAccessFile(srcFile, "r").getChannel();
		FileChannel desrFileChannel = new RandomAccessFile(destFile, "rw").getChannel();

		srcFileChannel.transferTo(0, srcFile.length(), desrFileChannel);

		long endTime = System.currentTimeMillis();
		float execTime = (float)(endTime - startTime) / 1000;
		System.out.println(execTime);
	}
}
