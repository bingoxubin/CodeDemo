package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/10/7 12:36 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountWords {
	//Leetcode 434. 字符串中的单词数
	//示例：输入：", , , ,        a, eaefa"   输出：6
	//分析：统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。请注意，你可以假定字符串里不包括任何不可打印的字符。
	//思路：方式一：使用内置函数
	//     方式二：使用字符判断
	public static void main(String[] args) {
		String str = ", , , ,        a, eaefa";
		CountWords countWords = new CountWords();
		System.out.println(countWords.countSegments1(str));
		System.out.println(countWords.countSegments2(str));
	}

	//方式一：使用内置函数
	public int countSegments1(String s) {
		//去掉收尾空格
		String str = s.trim();
		//如果是空字符串 返回0
		if (str.equals("")) return 0;
		//将字符串进行分隔
		//如果是split(" ")会出现问题，比如例子中，逗号跟字符中间的空格也被算做字符，需要用\\s+进行分隔
		String[] res = str.split("\\s+");
		return res.length;
	}

	//方式二：使用字符判断
	public int countSegments2(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
				count++;
			}
		}
		return count;
	}
}
