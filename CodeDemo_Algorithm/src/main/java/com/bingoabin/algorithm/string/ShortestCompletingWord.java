package com.bingoabin.algorithm.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: xubin34
 * @Date: 2021/12/10 9:03 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ShortestCompletingWord {
	//Leetcode 748. 最短补全词
	//示例：输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
	//     输出："steps"
	//     解释：最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
	//     "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
	//     "steps" 包含 "t"、"p" 和两个 "s"。
	//     "stripe" 缺一个 "s"。
	//     "stepple" 缺一个 "s"。
	//     因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。
	//分析：给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
	//      补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
	//      在匹配 licensePlate 中的字母时：
	//      忽略licensePlate 中的 数字和空格 。
	//      不区分大小写。
	//      如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
	//      例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
	//      请你找出并返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 最靠前的 那个。
	//思路：
	public static void main(String[] args) {
		String licensePlate = "1s3 PSt";
		String[] words = {"step", "steps", "stripe", "stepple"};
		ShortestCompletingWord shortestCompletingWord = new ShortestCompletingWord();
		System.out.println(shortestCompletingWord.shortestCompletingWord(licensePlate, words));
	}

	public String shortestCompletingWord(String licensePlate, String[] words) {
		licensePlate = licensePlate.replaceAll(" ", "").toLowerCase();
		int[] target = countWord(licensePlate);
		Arrays.sort(words, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		for (int i = 0; i < words.length; i++) {
			int[] temp = countWord(words[i].toLowerCase());
			if (isSimple(temp, target)) return words[i];
		}
		return "";
	}

	public boolean isSimple(int[] arr1, int[] arr2) {
		for (int i = 0; i < 26; i++) {
			if (arr1[i] < arr2[i]) return false;
		}
		return true;
	}

	public int[] countWord(String words) {
		int[] arr = new int[26];
		for (int i = 0; i < words.length(); i++) {
			if ((words.charAt(i) - 'a') >= 0 && (words.charAt(i) - 'a') < 26)
				arr[words.charAt(i) - 'a']++;
		}
		return arr;
	}
}
