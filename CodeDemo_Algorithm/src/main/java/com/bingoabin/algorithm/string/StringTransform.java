package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/7 10:05 上午
 */
public class StringTransform {
	//NC89 字符串变形
	//样例：输入"This is a sample",16 输出："SAMPLE A IS tHIS"
	//分析：首先将字符串以空格进行收尾翻转，然后字符以大小写翻转
	//思路：按照字符串的每个字符从头开始遍历到最后，如果是字母 小写转大写，如果是空格，将空格添加到临时单词的前面，然后拼接到结果集res的前面，最后返回结果
	public static void main(String[] args) {
		String str = "This is a sample";
		System.out.println(trans(str, 16));
	}

	public static String trans(String s, int n) {
		String res = "";
		String temp = "";
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				temp += Character.toUpperCase(ch);
			} else if (ch >= 'A' && ch <= 'Z') {
				temp += Character.toLowerCase(ch);
			} else {
				temp = ch + temp;
				res = temp + res;
				temp = "";
			}
		}
		res = temp + res;
		return res;
	}
}
