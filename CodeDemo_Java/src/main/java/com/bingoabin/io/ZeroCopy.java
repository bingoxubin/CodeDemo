package com.bingoabin.io;

import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 10:16 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ZeroCopy {
	@Test
	//普通拷贝技术  同步阻塞
	public void normalCopy() throws IOException {
		long startTime = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream("/Users/xubin34/postman/Postman-osx-8.8.0.zip");
		FileOutputStream fos = new FileOutputStream("/Users/xubin34/postman/Postman-osx-8.8.0_copy.zip");

		byte[] buffer = new byte[4096];
		while (fis.read(buffer) >= 0) {
			fos.write(buffer);
		}
		fos.flush();

		long endTime = System.currentTimeMillis();
		float resTime = (float) (endTime - startTime) / 1000;
		System.out.println("执行时间：" + (endTime - startTime) + "s");
	}

	@Test
	//零拷贝技术
	public void zeroCopy() throws IOException {
		long startTime = System.currentTimeMillis();
		File srcFile = new File("/Users/xubin34/postman/Postman-osx-8.8.0.zip");
		File targFile = new File("/Users/xubin34/postman/Postman-osx-8.8.0_copy.zip");

		FileChannel srcFileChannel = new RandomAccessFile(srcFile, "r").getChannel();
		FileChannel targFileChannel = new RandomAccessFile(targFile, "rw").getChannel();

		srcFileChannel.transferTo(0, srcFile.length(), targFileChannel);

		long endTime = System.currentTimeMillis();
		float resTime = (float) (endTime - startTime) / 1000;
		System.out.println("执行时间：" + (endTime - startTime) + "s");
	}
}
