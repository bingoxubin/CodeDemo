package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/5/16 1:48 上午
 */
public class SentenceSort {
	//Leetcode 5742. 将句子排序  https://leetcode-cn.com/problems/sorting-the-sentence/
	//样例：输入"is2 sentence4 This1 a3" 输出："This is a sentence"
	//分析：给一个字符串，每个单词最后都有数字表示序号，从1开始，以空格分隔
	//思路：通过空格进行分隔句子先，建立一个一样长度的数组，然后取最后的数字，放入到对应数组（获取的数字 - 1）的数组位置，最后进行拼接
	public static void main(String[] args) {
		String str = "is2 sentence4 This1 a3";
		System.out.println(sortSentence(str));
	}

	public static String sortSentence(String s) {
		String[] strs = s.split(" ");
		String[] words = new String[strs.length];
		for (String str : strs) {
			//将每个单词的最后一个数字 - 1 作为数组下标放到words数组中，对应放的值就是数字前面的单词
			words[Integer.parseInt(str.substring(str.length() - 1)) - 1] = str.substring(0, str.length() - 1);
		}
		//合并结果，组成新的句子
		StringBuilder res = new StringBuilder();
		for (String word : words) {
			res.append(word + " ");
		}
		//将句子最后的一个空格去掉
		return res.toString().substring(0, res.length() - 1);
	}
}
