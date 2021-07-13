package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/9 2:10 下午
 */
public class TranslateNumToString {
	//NC116 把数字翻译成字符串
	//样例：输入"12"   返回值 2   说明：2种可能的译码结果（”ab” 或”l”）
	//分析：有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。现在给一串数字，返回有多少种可能的译码结果
	//思路：采用动态规划的方式，定义个dp数组，第0个开始是1种可能，从位置1开始遍历，如果不是0，那么这个位置等于前一个位置的值，如果是0，那么往后判断；
	//     如果这个位置跟前一个位置组合起来在10=26之前那么  dp[i] += dp[i-2] 当然如果i == 1的时候 dp[i] += 1;
	public static void main(String[] args) {
		String str = "31717126241541717";
		String str1 = "12258";
		String str2 = "1203040102";
		System.out.println(solve(str));
		System.out.println(solve(str1));
		System.out.println(solve(str2));
	}

	public static int solve(String nums) {
		if (nums.length() == 0 || nums.charAt(0) == '0')return 0;
		int[] dp = new int[nums.length()];
		dp[0] = 1;
		for (int i = 1; i < nums.length(); i++) {
			if (nums.charAt(i) != '0') {
				dp[i] = dp[i - 1];
			}
			int num = (nums.charAt(i - 1) - '0') * 10 + (nums.charAt(i) - '0');
			if (num >= 10 && num <= 26) {
				if (i == 1) {
					dp[i] += 1;
				} else {
					dp[i] += dp[i - 2];
				}
			}
		}
		return dp[nums.length() - 1];
	}
}
