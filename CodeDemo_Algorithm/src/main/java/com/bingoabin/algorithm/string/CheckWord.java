package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/11/13 11:52 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CheckWord {
	//Leetcode 520. 检测大写字母
	//示例：示例 1：
	//     输入：word = "USA"
	//     输出：true
	//     示例 2：
	//     输入：word = "FlaG"
	//     输出：false
	//分析：检测一个单词是否是正确的，全部大写，或者全部小写，或者首字母大写才是正确的
	//思路：通过统计单词的大写字母的个数，然后判断大写字母的个数是否等于字符串长度，或者是否没有，或者只有一个并且是首字母，才返回true
	public static void main(String[] args) {
		String str1 = "USA";
		String str2 = "FlaG";
		CheckWord checkWord = new CheckWord();
		System.out.println(checkWord.detectCapitalUse(str1));
		System.out.println(checkWord.detectCapitalUse(str2));
	}

	public boolean detectCapitalUse(String word) {
		int count = 0;
		for (Character c : word.toCharArray()) {
			if (c >= 'A' && c <= 'Z') {
				count++;
			}
		}
		return count == word.length() || count == 0 || (count == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z');
	}
}
