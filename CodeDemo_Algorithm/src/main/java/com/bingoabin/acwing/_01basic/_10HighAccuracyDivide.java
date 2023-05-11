package com.bingoabin.acwing._01basic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/9 17:20
 * @Description:
 */
public class _10HighAccuracyDivide {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int b = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		for (int i = str.length() - 1; i >= 0; i--) list.add(str.charAt(i) - '0');

		List<Integer> res = getDivide(list, b);
		for (int i = res.size() - 2; i >= 0; i--) {
			System.out.print(res.get(i));
		}

		System.out.println();
		System.out.println(res.get(res.size() - 1));

		//方式二：
		BigInteger bi1 = new BigInteger(str);
		BigInteger bi2 = new BigInteger(String.valueOf(b));
		BigInteger[] ans = bi1.divideAndRemainder(bi2);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}

	public static List<Integer> getDivide(List<Integer> list, int num) {
		List<Integer> res = new ArrayList<>();
		int temp = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			temp = 10 * temp + list.get(i);
			res.add(temp / num);
			temp %= num;
		}
		Collections.reverse(res);
		while (res.size() > 1 && res.get(res.size() - 1) == 0) res.remove(res.size() - 1);
		res.add(temp);
		return res;
	}
}
