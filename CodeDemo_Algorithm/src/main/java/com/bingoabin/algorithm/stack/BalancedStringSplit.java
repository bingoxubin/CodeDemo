package com.bingoabin.algorithm.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author: xubin34
 * @Date: 2021/9/7 9:58 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class BalancedStringSplit {
	//Leetcode 1221. 分割平衡字符串
	//示例：输入：s = "RLRRLLRLRL"   输出：4   解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
	//分析：在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。   给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
	//思路：方式一：贪心算法
	//     方式二：hashmap
	//     方式三：栈实现
	public static void main(String[] args) {
		String str = "RLRRLLRLRL";
		BalancedStringSplit balancedStringSplit = new BalancedStringSplit();
		System.out.println(balancedStringSplit.balancedStringSplit1(str));
		System.out.println(balancedStringSplit.balancedStringSplit2(str));
		System.out.println(balancedStringSplit.balancedStringSplit3(str));
	}

	//方式一：贪心算法
	public int balancedStringSplit1(String s) {
		int count = 0;
		int res = 0;
		for (char ch : s.toCharArray()) {
			if (ch == 'L') {
				count++;
			} else {
				count--;
			}
			if (count == 0) res++;
		}
		return res;
	}

	//方式二：hashmap
	public int balancedStringSplit2(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		int res = 0;
		for (char ch : s.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
			if (map.containsKey('L') && map.containsKey('R') && map.get('L').equals(map.get('R'))) {
				res++;
			}
		}
		return res;
	}

	//方式三：栈
	public int balancedStringSplit3(String s) {
		Deque<Character> stack = new LinkedList<>();
		int res = 0;
		for (char ch : s.toCharArray()) {
			if (stack.isEmpty() || stack.peek() == ch) {
				stack.push(ch);
			} else {
				stack.pop();
			}
			if (stack.isEmpty()) {
				res++;
			}
		}
		return res;
	}
}
