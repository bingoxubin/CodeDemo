package com.bingoabin.exception;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/5 14:44
 * @Description:
 */
public class ThrowsTest {
	private static void foo() throws IOException, NoSuchFieldException {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		if (x == 1)
			throw new IOException("找不到文件！！！");
		else
			throw new NoSuchFieldException("自定义异常");
	}

	public static void main(String[] args) {
		try {
			foo();
		} catch (IOException e) {
			System.out.println("IOException!");
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException!");
			e.printStackTrace();
		}
	}
}
