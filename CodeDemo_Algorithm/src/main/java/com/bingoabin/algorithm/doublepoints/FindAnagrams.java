package com.bingoabin.algorithm.doublepoints;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/11/29 12:52 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindAnagrams {
	//Leetcode 438. 找到字符串中所有字母异位词
	//示例：输入: s = "abab", p = "ab"
	//     输出: [0,1,2]
	//     解释:
	//     起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
	//     起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
	//     起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
	//分析：给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
	//     异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
	//思路：双指针
	public static void main(String[] args) {
		String s = "abab";
		String p = "ab";
		FindAnagrams findAnagrams = new FindAnagrams();
		System.out.println(findAnagrams.findAnagrams(s, p));
	}

	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<Integer>();
		int[] pArr = new int[26]; //记录p字符串中的字符情况
		int[] sArr = new int[26]; //记录范围内的字符个数
		//统计p中的字符个数
		for (int i = 0; i < p.length(); i++) {
			pArr[p.charAt(i) - 'a']++;
		}
		for (int l = 0, r = 0; r < s.length(); r++) {
			//将右边界放入到数组中
			sArr[s.charAt(r) - 'a']++;
			//如果中间距离这段长度大于p，那么左边的记录删掉
			if (r - l + 1 > p.length()) {
				sArr[s.charAt(l++) - 'a']--;
			}
			if (r - l + 1 == p.length() && check(pArr, sArr)) {
				res.add(l);
			}
		}
		return res;
	}

	public boolean check(int[] arr1, int[] arr2) {
		for (int i = 0; i < 26; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
}
