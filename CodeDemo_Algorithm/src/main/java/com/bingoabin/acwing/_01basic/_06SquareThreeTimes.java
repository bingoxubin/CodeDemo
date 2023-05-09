package com.bingoabin.acwing._01basic;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/9 16:31
 * @Description:
 */
public class _06SquareThreeTimes {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double n = sc.nextDouble();
		double left = -1000;
		double right = 1000;
		double res = 0;
		while (left <= right - 1e-8) {
			double mid = (left + right) / 2;
			if (mid * mid * mid <= n) {
				res = mid;
				left = mid;
			} else {
				right = mid;
			}
		}
		System.out.printf("%.6f", res);
	}
}
