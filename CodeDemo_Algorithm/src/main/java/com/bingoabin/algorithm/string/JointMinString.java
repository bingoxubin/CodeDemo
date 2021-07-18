package com.bingoabin.algorithm.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xubin03
 * @date 2021/6/6 8:08 下午
 */
public class JointMinString {
	//NC85 拼接所有的字符串产生字典序最小的字符串
	//样例：输入["abc","de"] 输出：abcde
	//分析：给定一个字符串的数组strs，请找到一种拼接顺序，使得所有的字符串拼接起来组成的字符串是所有可能性中字典序最小的，并返回这个字符串。
	//思路:通过自定义排序方式，将数组中的字符串进行排序，然后进行拼接
	public static void main(String[] args) {
		String[] arr = {"abc", "de"};
		System.out.println(minString(arr));
	}

	public static String minString(String[] strs) {
		Arrays.sort(strs, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o1 + o2).compareTo(o2 + o1);
			}
		});
		StringBuilder res = new StringBuilder();
		for (String str : strs) {
			res.append(str);
		}
		return res.toString();
	}
}
