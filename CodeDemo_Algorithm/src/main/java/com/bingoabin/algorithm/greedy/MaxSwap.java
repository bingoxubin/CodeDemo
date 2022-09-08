package com.bingoabin.algorithm.greedy;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/9/8 14:56
 * @Description: 交换一次的最大数
 */
public class MaxSwap {
	public static void main(String[] args) {
		MaxSwap maxSwap = new MaxSwap();
		System.out.println(maxSwap.maximumSwap(326587));
	}

	//贪心算法
	public int maximumSwap(int num) {
		String str = String.valueOf(num);
		char[] ch = str.toCharArray();
		int[] dp = new int[ch.length];
		int maxIndex = ch.length - 1;
		for (int i = ch.length - 1; i >= 0; i--) {
			if (ch[i] > ch[maxIndex]) {
				maxIndex = i;
			}
			dp[i] = maxIndex;
		}
		System.out.println(Arrays.toString(dp));
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] != ch[dp[i]]) {
				char temp = ch[dp[i]];
				ch[dp[i]] = ch[i];
				ch[i] = temp;
				break;
			}
		}
		return Integer.parseInt(new String(ch));
	}
}
