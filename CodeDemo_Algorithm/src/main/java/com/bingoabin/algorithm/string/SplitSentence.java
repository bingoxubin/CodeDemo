package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/12/6 9:07 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SplitSentence {
	//Leetcode 1816. 截断句子
	//示例：输入：s = "Hello how are you Contestant", k = 4    输出："Hello how are you"
	//分析：句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
	//     例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
	//     给你一个句子 s 和一个整数 k ，请你将 s截断，使截断后的句子仅含 前 k 个单词。返回 截断 s后得到的句子。
	//思路：通过循环遍历每个字符进行计算
	public static void main(String[] args) {
		String str = "Hello how are you Contestant";
		SplitSentence splitSentence = new SplitSentence();
		System.out.println(splitSentence.truncateSentence(str, 4));
	}

	public String truncateSentence(String s, int k) {
		StringBuilder res = new StringBuilder();
		int len = s.length();
		for (int i = 0, cnt = 0; i < len && cnt < k; i++) {
			char ch = s.charAt(i);
			if (ch == ' ') {
				cnt++;
			}
			if (cnt < k) {
				res.append(ch);
			}
		}
		return res.toString();
	}
}
