package com.bingoabin.algorithm.dp;

/**
 * @Author: xubin34
 * @Date: 2021/9/11 11:56 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindInteger {
	//Leetcode 600. 不含连续1的非负整数
	//示例：输入: 5  输出: 5
	//     解释:
	//     下面是带有相应二进制表示的非负整数<= 5：
	//     0 : 0
	//     1 : 1
	//     2 : 10
	//     3 : 11
	//     4 : 100
	//     5 : 101
	//     其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。

	//分析：给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
	//思路：先用动态规划求出n位二进制数满足条件的个数，然后将目标数转化为二进制字符串，然后解析二进制字符串，找到1的位置，小于该值的可能数 dp[len - i - 1];
	public static void main(String[] args) {
		FindInteger findInteger = new FindInteger();
		System.out.println(findInteger.findIntegers(5));
	}

	public int findIntegers(int num) {
		//i下标值的二进制位数 满足条件的数据个数
		int[] dp = new int[32];
		dp[0] = 1;
		dp[1] = 2;
		dp[2] = 3;
		for (int i = 3; i < 32; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		//将目标数转化为二进制字符串
		String str = getBinary(num);
		int res = 0;
		for (int i = 0; i < str.length(); i++) {
			//遍历字符串，如果该位为0  直接继续
			if (str.charAt(i) == '0') continue;
			//如果为1  那么就加上小于改数的 位数 的对应的可能情况
			res += dp[str.length() - i - 1];
			// 如果当前位 前面1位 也是1  那么后面所有可能都不存在了 只要求前面一种情况  直接返回结果值即可
			if (i != 0 && str.charAt(i - 1) == '1') return res;
		}
		//如果上面的if没走  最终结果加上最后一种情况
		return res + 1;
	}

	//将数字转化为二进制字符串
	public String getBinary(int num) {
		StringBuilder res = new StringBuilder();
		while (num > 0) {
			res.insert(0, num & 1);
			num >>= 1;
		}
		return res.toString();
	}
}
