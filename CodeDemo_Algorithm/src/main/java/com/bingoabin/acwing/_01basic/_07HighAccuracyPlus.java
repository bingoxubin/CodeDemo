package com.bingoabin.acwing._01basic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/9 17:18
 * @Description:
 */
public class _07HighAccuracyPlus {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();
		List<Integer> num1 = new ArrayList<>();
		List<Integer> num2 = new ArrayList<>();

		for (int i = a.length() - 1; i >= 0; i--) num1.add(a.charAt(i) - '0');
		for (int i = b.length() - 1; i >= 0; i--) num2.add(b.charAt(i) - '0');

		List<Integer> res = getSum(num1, num2);
		for (int i = res.size() - 1; i >= 0; i--) {
			System.out.print(res.get(i));
		}

		//方式二：
		System.out.println();
		BigInteger b1 = new BigInteger(a);
		BigInteger b2 = new BigInteger(b);
		System.out.println(b1.add(b2));
	}

	//方式一：
	public static List<Integer> getSum(List<Integer> num1, List<Integer> num2) {
		List<Integer> res = new ArrayList<>();
		int temp = 0;
		int i = 0;
		int j = 0;
		while (i < num1.size() || j < num2.size() || temp != 0) {
			int n1 = i < num1.size() ? num1.get(i++) : 0;
			int n2 = j < num2.size() ? num2.get(j++) : 0;
			temp = n1 + n2 + temp;
			res.add(temp % 10);
			temp /= 10;
		}
		return res;
	}
}
