package com.bingoabin.acwing._01basic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/9 17:19
 * @Description:
 */
public class _08HighAccuracyMinus {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();

		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		for (int i = a.length() - 1; i >= 0; i--) list1.add(a.charAt(i) - '0');
		for (int i = b.length() - 1; i >= 0; i--) list2.add(b.charAt(i) - '0');

		if (!isBetter(list1, list2)) {
			System.out.print("-");
		}

		List<Integer> res = getMinues(list1, list2);
		for (int i = res.size() - 1; i >= 0; i--) {
			System.out.print(res.get(i));
		}

		//方式二：
		BigInteger bi1 = new BigInteger(a);
		BigInteger bi2 = new BigInteger(b);
		System.out.println(bi1.subtract(bi2));
	}

	//求差
	public static List<Integer> getMinues(List<Integer> list1, List<Integer> list2) {
		if (!isBetter(list1, list2)) return getMinues(list2, list1);
		int temp = 0;
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < list1.size(); i++) {
			temp = list1.get(i) - temp;
			if (i < list2.size()) temp -= list2.get(i);
			res.add((temp + 10) % 10);
			if (temp >= 0) {
				temp = 0;
			} else {
				temp = 1;
			}
		}
		while (res.size() > 1 && res.get(res.size() - 1) == 0) res.remove(res.size() - 1);
		return res;
	}

	//判断哪个数大一些
	public static boolean isBetter(List<Integer> list1, List<Integer> list2) {
		if (list1.size() != list2.size()) return list1.size() > list2.size();
		for (int i = list1.size() - 1; i >= 0; i--) {
			if (list1.get(i) != list2.get(i)) {
				return list1.get(i) > list2.get(i);
			}
		}
		return true;
	}
}
