package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/9/21 10:35 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LastLengthOfWords {
	//Leetcode 58. 最后一个单词的长度
	//示例：输入：s = "   fly me   to   the moon  "   输出：4
	//分析：给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
	//思路：现将字符串的前后空格取消掉 然后找到最后一个空格字符  如果不存在空格 那么就一个单词 返回单词长度  如果找到最后一个空格 那么就是长度减去空格位置 - 1
	public static void main(String[] args) {
		String str = "   fly me   to   the moon  ";
		LastLengthOfWords lastLengthOfWords = new LastLengthOfWords();
		System.out.println(lastLengthOfWords.lengthOfLastWord(str));
		System.out.println(lastLengthOfWords.lengthOfLastWord2(str));
	}

	//方式一：现将字符串的前后空格取消掉 然后找到最后一个空格字符  如果不存在空格 那么就一个单词 返回单词长度  如果找到最后一个空格 那么就是长度减去空格位置 - 1
	public int lengthOfLastWord(String s) {
		String res = s.trim();
		int index = res.lastIndexOf(' ');
		if (index == -1) return res.length();
		return res.length() - index - 1;
	}

	//方式二：先将前后的空格取消掉  然后从后往前遍历 如果不是空格 那么长度+1  如果是空格  退出  返回长度即可
	public int lengthOfLastWord2(String s) {
		int result = 0;
		s = s.trim();
		char[] temp = s.toCharArray();
		if (temp.length == 0) {
			return 0;
		}
		for (int i = temp.length - 1; i >= 0; i--) {
			if (s.charAt(i) != ' ') {
				result++;
			} else {
				break;
			}
		}
		return result;
	}
}
