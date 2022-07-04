package com.bingoabin.algorithm.string;

/**
 * @author bingoabin
 * @date 2022/7/4 17:22
 */
public class VertifyWordValid {
	//Leetcode 408. 有效单词缩写
	//示例：示例 1:
	//      给定 s = "internationalization", abbr = "i12iz4n":
	//      函数返回 true.
	//      示例 2:
	//      给定 s = "apple", abbr = "a2e":
	//      函数返回 false.
	//分析：给一个 非空 字符串 s 和一个单词缩写 abbr ，判断这个缩写是否可以是给定单词的缩写。
	//      字符串 "word" 的所有有效缩写为：
	//      ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
	//      注意单词 "word" 的所有有效缩写仅包含以上这些。任何其他的字符串都不是 "word" 的有效缩写。
	//      注意:
	//      假设字符串 s 仅包含小写字母且 abbr 只包含小写字母和数字。
	//思路：
	public static void main(String[] args) {
		VertifyWordValid vertifyWordValid = new VertifyWordValid();
		System.out.println(vertifyWordValid.validWordAbbreviation("internationalization", "i12iz4n"));
	}

	public boolean validWordAbbreviation1(String word, String abbr) {
		char[] arr = abbr.toCharArray();
		int index = 0;
		int sum = 0;
		for (char ch : arr) {
			if (ch >= '0' && ch <= '9') {
				if (sum == 0 && ch == '0') return false;
				sum = 10 * sum + (ch - '0');
				continue;
			}
			index = sum + index;
			if (index > word.length() || word.charAt(index) != ch) return false;
			index++;
			sum = 0;
		}
		return sum + index == word.length();
	}

	public boolean validWordAbbreviation(String word, String abbr) {
		char[] arrs = abbr.toCharArray();
		int index = 0;
		int sum = 0;
		for (char arr : arrs) {
			if (arr >= '0' && arr <= '9') {
				if (sum == 0 && arr == '0') return false;
				sum = sum * 10 + (arr - '0');
				continue;
			}
			index = sum + index;
			if (index > word.length() || word.charAt(index) != arr) return false;
			index++;
			sum = 0;
		}
		return index + sum == word.length();
	}
}
