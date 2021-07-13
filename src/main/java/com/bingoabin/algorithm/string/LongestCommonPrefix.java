package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/9 7:36 下午
 */
public class LongestCommonPrefix {
	//NC55 最长公共前缀
	//样例：输入["abca","abc","abca","abc","abcc"] 输出：abc
	//分析：查找字符串数组中的最长公共前缀。
	//思路：从第一个数组中的字符串总长度开始缩减，如果后面的startwith，为true，那么继续，如果为false那么不断缩减字符串长度
	public static void main(String[] args) {
		String[] res = {"abca", "abc", "abca", "abc", "abcc"};
		System.out.println(longestCommonPrefix1(res));
	}

	//从第一个数组中的字符串总长度开始缩减，如果后面的startwith，为true，那么继续，如果为false那么不断缩减字符串长度
	public static String longestCommonPrefix1(String[] strs) {
		String res = "";
		if (strs.length > 0) res = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (!strs[i].startsWith(res)) {
				res = res.substring(0, res.length() - 1);
			}
		}
		return res;
	}
}
