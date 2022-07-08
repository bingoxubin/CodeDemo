package com.bingoabin.algorithm.string;

/**
 * @author bingoabin
 * @date 2022/7/8 14:09
 */
public class CountCharacters {
	//Leetcode 1160. 拼写单词
	//示例：示例 1：
	//      输入：words = ["cat","bt","hat","tree"], chars = "atach"
	//      输出：6
	//      解释：
	//      可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
	//      示例 2：
	//      输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
	//      输出：10
	//      解释：
	//      可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
	//分析：给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
	//      假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
	//      注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
	//      返回词汇表 words 中你掌握的所有单词的 长度之和。
	//思路：
	public static void main(String[] args) {
		CountCharacters countCharacters = new CountCharacters();
		String[] words = {"hello", "world", "leetcode"};
		String chars = "welldonehoneyr";
		System.out.println(countCharacters.countCharacters(words, chars));
	}

	public int countCharacters(String[] words, String chars) {
		int[] arrs = new int[26];
		for (char ch : chars.toCharArray()) arrs[ch - 'a']++;
		int res = 0;
		search:
		for (String word : words) {
			int[] temp = new int[26];
			for (char ch : word.toCharArray()) temp[ch - 'a']++;
			for (int i = 0; i < 26; i++) {
				if (arrs[i] < temp[i]) {
					continue search;
				}
			}
			res += word.length();
		}
		return res;
	}
}
