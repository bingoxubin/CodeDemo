package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/9/20 3:40 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TwoKeyboard {
	//LeetCode 650. 只有两个键的键盘
	//示例：输入：3   输出：3
	//     解释：
	//     最初, 只有一个字符 'A'。
	//     第 1 步, 使用 Copy All 操作。
	//     第 2 步, 使用 Paste 操作来获得 'AA'。
	//     第 3 步, 使用 Paste 操作来获得 'AAA'。
	//分析：最开始笔记本上只有一个'A',每次可以对这个笔记本上可以有两种操作，
	//     1.复制笔记本上的所有字符（不允许部分字符）
	//     2.黏贴上一次复制的字符，
	//     给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
	//     比如：17 那么返回就是17 copy1次 paste16次
	//          64 那么返回就是12   copy1次paste1次有2个A 再copy一次paste一次有4个A 再copy一次paste一次有8个A 再copy一次paste一次有16个A 再copy一次paste一次有32个A 再copy一次paste一次有64个A
	//思路：方式一：从2的平方开始，3的平方，如果平方值<n  用while循环来，如果能除尽，那么res += i n=n/i 继续进行  最终res += n
	//     方式二：动态规划
	public static void main(String[] args) {
		TwoKeyboard twoKeyboard = new TwoKeyboard();
		System.out.println(twoKeyboard.minSteps1(5));
		System.out.println(twoKeyboard.minSteps1(17));
		System.out.println(twoKeyboard.minSteps1(64));

		System.out.println(twoKeyboard.minSteps2(5));
		System.out.println(twoKeyboard.minSteps2(17));
		System.out.println(twoKeyboard.minSteps2(64));
	}

	//方式一：从2的平方开始，3的平方，如果平方值<n  用while循环来，如果能除尽，那么res += i n=n/i 继续进行  最终res += n
	public int minSteps1(int n) {
		int res = 0;
		for (int i = 2; i * i < n; i++) {
			while (n % i == 0) {
				res += i;
				n /= i;
			}
		}
		if (n != 1) res += n;
		return res;
	}

	//方式二：动态规划
	public int minSteps2(int n) {
		int INF = 0x3f3f3f3f;
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				dp[i][j] = INF;
			}
		}
		// int[] rowdp = new int[n + 1];
		// Arrays.fill(rowdp, INF);
		// Arrays.fill(dp, rowdp);
		dp[1][0] = 0;
		dp[1][1] = 1;
		for (int i = 2; i <= n; i++) {
			int min = INF;
			for (int j = 0; j <= i / 2; j++) {
				dp[i][j] = dp[i - j][j] + 1;
				min = Math.min(min, dp[i][j]);
			}
			dp[i][i] = min + 1;
		}
		int ans = INF;
		for (int i = 0; i <= n; i++) ans = Math.min(ans, dp[n][i]);
		return ans;
	}
}
