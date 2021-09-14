package com.bingoabin.algorithm.sort;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2021/9/14 10:26 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindLongestString {
	//Leetcode 524. 通过删除字母匹配到字典里最长单词
	//示例：输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"] 输出："apple"
	//分析：将字符串s其中删除部分字符  可以得到最长的字符串  能匹配dictinoary 就得到最长的字符串，如果出现多个  随意返回一个
	//思路：方式一：将字典数组进行排序，按长度排序  然后与s进行比较
	//     方式二：直接通过双指针进行对比比较
	public static void main(String[] args) {
		String s = "abpcplea";
		List<String> dictionary = new ArrayList<>(Arrays.asList("ale", "apple", "monkey", "plea"));
		FindLongestString findLongestString = new FindLongestString();
		System.out.println(findLongestString.findLongestWord1(s, dictionary));
		System.out.println(findLongestString.findLongestWord2(s, dictionary));
	}

	//方式一：将字典数组进行排序，按长度排序  然后与s进行比较
	public String findLongestWord1(String s, List<String> dictionary) {
		Collections.sort(dictionary, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() != o2.length()) {
					return o2.length() - o1.length();
				} else {
					return o1.compareTo(o2);
				}
			}
		});
		for (String dic : dictionary) {
			int i = 0;
			int j = 0;
			while (i < dic.length() && j < s.length()) {
				if (dic.charAt(i) == s.charAt(j)) {
					i++;
				}
				j++;
			}
			if (i == dic.length()) {
				return dic;
			}
		}

		return "";
	}

	//方式二：直接通过双指针进行对比比较,将每一个字典数组中的字符串拿出来与s进行比较  如果满足条件 缓存到res 然后到res中进行删选
	public String findLongestWord2(String s, List<String> dictionary) {
		String res = "";
		for (String dic : dictionary) {
			int i = 0;
			int j = 0;
			while (i < dic.length() && j < s.length()) {
				if (dic.charAt(i) == s.charAt(j)) {
					i++;
				}
				j++;
			}
			if (i == dic.length()) {
				if (dic.length() > res.length() || (dic.length() == res.length() && dic.compareTo(res) < 0)) {
					res = dic;
				}
			}
		}
		return res;
	}
}
