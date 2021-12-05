package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/12/5 8:55 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CanConstruct {
	//Leetcode 383. 赎金信
	//示例：输入：ransomNote = "aa", magazine = "ab"  输出：false
	//分析：为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
	//     给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
	//     如果可以构成，返回 true ；否则返回 false 。
	//     magazine 中的每个字符只能在 ransomNote 中使用一次。
	//思路：
	public static void main(String[] args) {
		String ransomNode = "aa";
		String magazine = "ab";
		CanConstruct canConstruct = new CanConstruct();
		System.out.println(canConstruct.canConstruct1(ransomNode, magazine));
		System.out.println(canConstruct.canConstruct2(ransomNode, magazine));
	}

	//方式一：两个数组
	public boolean canConstruct1(String ransomNote, String magazine) {
		int[] arr1 = new int[26];
		for (char ch1 : ransomNote.toCharArray()) {
			arr1[ch1 - 'a']++;
		}

		int[] arr2 = new int[26];
		for (char ch2 : magazine.toCharArray()) {
			arr2[ch2 - 'a']++;
		}

		for (int i = 0; i < 26; i++) {
			if (arr2[i] < arr1[i]) {
				return false;
			}
		}
		return true;
	}

	//方式二：一个数组
	public boolean canConstruct2(String ransomNode, String magazine) {
		int[] arr = new int[26];
		for (char ch : magazine.toCharArray()) {
			arr[ch - 'a']++;
		}

		for (char ch : ransomNode.toCharArray()) {
			arr[ch - 'a']--;
			if (arr[ch - 'a'] < 0) {
				return false;
			}
		}
		return true;
	}
}
