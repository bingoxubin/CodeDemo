package com.bingoabin.finalize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author bingoabin
 * @date 2022/8/16 22:33
 * @Description: 垃圾回收前执行
 */
public class FinalizerDemo {
	private BufferedReader reader;

	public FinalizerDemo(){
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("hello.txt");
		reader = new BufferedReader(new InputStreamReader(resourceAsStream));
	}

	public void print() throws IOException {
		reader.lines().forEach(System.out::println);
	}

	@Override
	protected void finalize() throws Throwable {
		if (reader != null) {
			reader.close();
			System.out.println("资源被关闭");
		}
	}

	public static void main(String[] args) throws IOException {
	    new FinalizerDemo().print();
		System.gc();
	}
}
