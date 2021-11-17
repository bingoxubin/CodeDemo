package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/11/17 9:36 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxLengthProduct {
	//Leetcode 318. 最大单词长度乘积
	//示例：输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
	//     输出: 16
	//     解释: 这两个单词为 "abcw", "xtfn"。
	//分析：给定一个字符串数组words，找到length(word[i]) * length(word[j])的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
	//思路：采用位运算的方式
	public static void main(String[] args) {
		String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
		MaxLengthProduct maxLengthProduct = new MaxLengthProduct();
		System.out.println(maxLengthProduct.maxProduct(words));
	}

	public int maxProduct(String[] words) {
		int len = words.length;
		//记录每个单词的进制
		int[] marks = new int[len];
		//遍历计算每个单词的进制
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				//将每个字母的26进制保存到数组当前位置中
				marks[i] |= 1 << (words[i].charAt(j) - 'a');
			}
		}
		int max = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				//统计出最大的乘积
				if ((marks[i] & marks[j]) == 0) {
					max = Math.max(max, words[i].length() * words[j].length());
				}
			}
		}
		return max;
	}
}
