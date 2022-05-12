package com.bingoabin.algorithm.string;

/**
 * @author bingoabin
 * @date 2022/4/26 15:06
 */
public class MaxString {
	public static void main(String[] args) {
		System.out.println(new MaxString().maximumSwap1(2736));
	}

	//暴力方式
	public int maximumSwap1(int num) {
		String nums = String.valueOf(num);
		char[] ch = nums.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			for (int j = i + 1; j < ch.length; j++) {
				swap(ch, i, j);
				nums = nums.compareTo(new String(ch)) < 0 ? new String(ch) : nums;
				swap(ch, i, j);
			}
		}
		return Integer.parseInt(nums);
	}

	public void swap(char[] ch, int i, int j) {
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}

	//贪心方式
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
