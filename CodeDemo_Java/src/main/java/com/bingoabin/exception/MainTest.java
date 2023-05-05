package com.bingoabin.exception;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/5 11:31
 * @Description:
 */
public class MainTest {
	private static void foo() {
		int[] array = new int[5];
		for (int i = 0; i < 5; i++)
			array[i] = i;

		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int x = sc.nextInt();

		try {
			array[k] /= x;
		} catch (ArithmeticException e) {
			System.out.println("除零错误！");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("数组越界！");
			e.printStackTrace();
		} finally {
			for (int i = 0; i < 5; i++) {
				System.out.print(array[i] + " ");
			}
		}
		//如果上面有return，那么这里就不会执行，而finally中都会执行
		// System.out.println("end");
	}

	public static void main(String[] args) {
		foo();
		System.out.println("111111111");
	}
}
