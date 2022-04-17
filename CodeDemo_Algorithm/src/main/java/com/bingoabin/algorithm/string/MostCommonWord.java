package com.bingoabin.algorithm.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author bingoabin
 * @date 2022/4/17 13:43
 */
public class MostCommonWord {
	//Leetcode 819. 最常见的单词
	//示例：输入:
	//      paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
	//      banned = ["hit"]
	//      输出: "ball"
	//      解释:
	//      "hit" 出现了3次，但它是一个禁用的单词。
	//      "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
	//      注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
	//      "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
	//分析：给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
	//      题目保证至少有一个词不在禁用列表中，而且答案唯一。
	//      禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
	//思路：
	public static void main(String[] args){
		String garagraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = {"hit"};
		MostCommonWord mostCommonWord = new MostCommonWord();
		System.out.println(mostCommonWord.mostCommonWord(garagraph,banned));
		System.out.println(mostCommonWord.mostCommonWord1(garagraph,banned));
	}

	//方式一：分割字符串
	public String mostCommonWord(String garagraph, String[] banned) {
		//字符串都转化成小写
		garagraph = garagraph.toLowerCase(Locale.ROOT);
		//将banned中的字符串放到set中
		Set<String> set = new HashSet<>();
		for(String ban:banned) set.add(ban);
		//计数
		HashMap<String, Integer> map = new HashMap<>();
		//分割字符串
		String[] words = garagraph.split("\\W+");
		//缓存结果值
		int max = 0;
		String res = "";
		for (String word : words) {
			if (!set.contains(word)) {
				map.put(word, map.getOrDefault(word, 0) + 1);
				if (map.get(word) > max) {
					max = map.get(word);
					res = word;
				}
			}
		}
		return res;
	}

	//方式二：分割字符
	public String mostCommonWord1(String garagraph, String[] banned) {
		//都转成小写
		garagraph = (garagraph + ".").toLowerCase(Locale.ROOT);
		//将banned中的字符串放到set中
		Set<String> set = new HashSet<>();
		for(String ban:banned) set.add(ban);
		//计数
		HashMap<String, Integer> map = new HashMap<>();
		//缓存结果
		String res = "";
		int max = 0;
		StringBuilder word = new StringBuilder();
		for (char ch : garagraph.toCharArray()) {
			if (Character.isLetter(ch)) {
				word.append(ch);
			}else{
				if(word.length() > 0){
					String temp = word.toString();
					if (!set.contains(temp)) {
						map.put(temp, map.getOrDefault(temp, 0) + 1);
						if(map.get(temp) > max){
							max = map.get(temp);
							res = temp;
						}
					}
				}
				word = new StringBuilder();
			}
		}
		return res;
	}
}
