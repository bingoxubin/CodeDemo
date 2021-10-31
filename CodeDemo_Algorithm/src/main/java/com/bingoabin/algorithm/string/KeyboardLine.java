package com.bingoabin.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/10/31 1:05 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class KeyboardLine {
	//Leetcode 500. 键盘行
	//示例：输入：words = ["Hello","Alaska","Dad","Peace"] 输出：["Alaska","Dad"]
	//分析：给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。美式键盘 中：
	//     第一行由字符 "qwertyuiop" 组成。
	//     第二行由字符 "asdfghjkl" 组成。
	//     第三行由字符 "zxcvbnm" 组成。
	//思路：
	public static void main(String[] args) {
		KeyboardLine keyboardLine = new KeyboardLine();
		String[] arr = {"Hello", "Alaska", "Dad", "Peace"};
		System.out.println(Arrays.toString(keyboardLine.findWords(arr)));
	}

	// 先将键盘中的字符 放入到map中，来区分第一行还是第二行 还是第三行
	public String[] findWords(String[] words) {
		// 先将键盘中的字符 放入到map中，来区分第一行还是第二行 还是第三行
		int[] map = {2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};
		// 创建结果集
		ArrayList<String> res = new ArrayList<>();
		// 遍历字符串数组中的每个字符串
		for (String word : words) {
			// 先把字符串全部转化成小写
			String temp = word.toLowerCase();
			// 判断字符串中第一个字符是在第几行
			int index = map[temp.charAt(0) - 'a'];
			// 用一个flag 记好 后面是否在一行中，如果不在一行，返回false 并且break
			boolean flag = true;
			// 从第二个字符开始判断，是否在一行中
			for (int i = 1; i < temp.length(); i++) {
				//如果后面的字符 跟一个字符不在一行中，那么flag = false并且退出循环，这个字符串不做记录
				if (map[temp.charAt(i) - 'a'] != index) {
					flag = false;
					break;
				}
			}
			if (flag) {
				res.add(word);
			}
		}
		return res.toArray(new String[res.size()]);
	}
}
