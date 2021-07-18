package com.bingoabin.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/23 10:26
 */
public class StringArrange {
	//剑指 Offer 38. 字符串的排列
	//样例：输入："abb"  输出："abb" "bba" bab"
	//分析：求出一个字符串的所有排列方式
	//思路：采用回溯的方式求
	// a - b - b
	// a - b - b  - 舍弃
	// b - a - b
	// b - b - a
	// b - a - b  - 舍弃
	// b - b - a  - 舍弃
	public static void main(String[] args) {
		String str = "abb";
		System.out.println(Arrays.toString(permutation(str)));
	}

	//采用递归方式，递归方法需要5个参数，一个原始数据arr数组，一个深度depth，一个boolean数组表示是否用过，一个中间结果值stringbuilder，一个最终结果ArrayList<String>
	public static String[] permutation(String s) {
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		ArrayList<String> res = new ArrayList<String>();
		boolean[] used = new boolean[arr.length];
		StringBuilder buf = new StringBuilder();
		dfs(arr, 0, used, buf, res);
		String[] ans = new String[res.size()];
		for (int i = 0; i < res.size(); i++) {
			ans[i] = res.get(i);
		}
		return ans;
	}

	//递归函数，当used[i]用过了  那就continue   或者  当i>0 而且char[i] = char[i-1] 并且 used[i-1]没用过 直接continue;
	public static void dfs(char[] arr, int depth, boolean[] used, StringBuilder buf, ArrayList<String> res) {
		if (depth == arr.length) {
			res.add(buf.toString());
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (used[i] || (i > 0 && !used[i - 1] && arr[i] == arr[i - 1])) {
				continue;
			}
			used[i] = true;
			buf.append(arr[i]);
			dfs(arr, depth + 1, used, buf, res);
			used[i] = false;
			buf.deleteCharAt(buf.length() - 1);
		}
	}
}
