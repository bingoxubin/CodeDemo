package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/6 8:22 下午
 */
public class CompareVersion {
	//NC104 比较版本号
	//样例：输入"0.1","1.1" 输出：-1
	//分析：给出两个字符串表示两个版本，前面一个大，返回1，后面一个大，返回-1，相同返回0  注意：1.0 和1.0.0是一样的
	//思路：先将两个字符串转化成char数组，然后统计出两个字符串的最大值，然后遍历最大值的长度，如果不够的话用0补上，如果前面的大返回1，后面的大返回-1 一样大继续遍历
	public static void main(String[] args) {
		String str1 = "0.1";
		String str2 = "1.1";
		System.out.println(compare(str1, str2));
	}

	public static int compare(String version1, String version2) {
		String[] str1 = version1.split("\\.");
		String[] str2 = version2.split("\\.");
		int len = Math.max(str1.length, str2.length);
		for (int i = 0; i < len; i++) {
			int a = i < str1.length ? Integer.parseInt(str1[i]) : 0;
			int b = i < str2.length ? Integer.parseInt(str2[i]) : 0;
			if (a > b) {
				return 1;
			} else if (a < b) {
				return -1;
			}
		}
		return 0;
	}
}
