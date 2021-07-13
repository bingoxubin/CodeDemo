package com.bingoabin.algorithm.string;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/6/8 1:04 上午
 */
public class WildcardCharacter {
	//NC44 通配符匹配
	//样例：输入"ab","?*" 输出：true
	//分析：'?' 可以匹配任何单个字符。'*' 可以匹配任何字符序列（包括空序列）。返回两个字符串是否匹配
	//思路：用动态规划的方式，定义dp数组，长度是n+1,在每个字符串前面加上一个空格，表示true起步，否则后面存在false的情况
	//     如果通用表达式pp，pp的这个位置上为*，那就只要比较前面一个或者上面一个只要为true就是true；如果不是*，那就要判断dp[i-1][j-1]为true，并且i j上的相等，或者j上的为？
	public static void main(String[] args) {
		String str1 = "aaaabbbbb";
		String str2 = "?*";
		System.out.println(isMatch(str1, str2));
	}

	public static boolean isMatch(String ss, String pp) {
		int lens = ss.length();
		int lenp = pp.length();
		ss = " " + ss;
		pp = " " + pp;
		boolean[][] dp = new boolean[lens + 1][lenp + 1];
		dp[0][0] = true;
		for (int i = 0; i <= lens; i++) {
			for (int j = 1; j <= lenp; j++) {
				if (pp.charAt(j) == '*') {
					dp[i][j] = (i > 0 && dp[i - 1][j]) || dp[i][j - 1];
				} else {
					dp[i][j] = (i > 0 && dp[i - 1][j - 1]) && (ss.charAt(i) == pp.charAt(j) || pp.charAt(j) == '?');
				}
			}
		}
		return dp[lens][lenp];
	}
}
