package com.bingoabin.algorithm.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/10/8 9:42 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RepeateDNA {
	//Leetcode 187. 重复的DNA序列
	//示例：输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"  输出：["AAAAACCCCC","CCCCCAAAAA"]
	//分析：编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
	//思路：使用hashmap累计统计次数
	public static void main(String[] args) {
		String str = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		RepeateDNA repeateDNA = new RepeateDNA();
		System.out.println(repeateDNA.findRepeatedDnaSequences(str));
	}

	//通过hashmap累计次数
	public List<String> findRepeatedDnaSequences(String s) {
		//创建结果集
		List<String> res = new ArrayList<>();
		//字符串总长度
		int len = s.length();
		//定义hashmap 保存字符串出现的次数
		HashMap<String, Integer> map = new HashMap<>();
		//从头开始遍历字符串，只遍历到i+10 <= len
		//比如一个字符串正好有10个字符  i可以取0,但是不能取1，因为取1的话后面只有9个字符了  所以必须 i+10 <= len
		for (int i = 0; i + 10 <= len; i++) {
			//当前10个字符的字符串
			String cur = s.substring(i, i + 10);
			//取出 在map中该字符串出现的次数
			int cnt = map.getOrDefault(cur, 0);
			//如果出现次数 == 1 那么放入到结果集中
			if (cnt == 1) res.add(cur);
			//然后将 map中字符串出现次数进行更新
			map.put(cur, cnt + 1);
		}
		return res;
	}
}
