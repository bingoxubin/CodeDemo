package com.bingoabin.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2022/7/8 17:12
 */
public class BalanceStringSplit {
	//Leetcode 1221. 分割平衡字符串
	//示例；示例 1：
	// 	    输入：s = "RLRRLLRLRL"
	// 	    输出：4
	// 	    解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
	// 	    示例 2：
	// 	    输入：s = "RLLLLRRRLR"
	// 	    输出：3
	// 	    解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
	// 	    示例 3：
	// 	    输入：s = "LLLLRRRR"
	// 	    输出：1
	// 	    解释：s 只能保持原样 "LLLLRRRR".
	//分析：在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
	// 	    给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
	// 	    返回可以通过分割得到的平衡字符串的最大数量。
	//思路：
	public static void main(String[] args) {
		BalanceStringSplit balanceStringSplit = new BalanceStringSplit();
		System.out.println(balanceStringSplit.balancedStringSplit("RLRRLLRLRL"));
		System.out.println(balanceStringSplit.balancedStringSplit1("RLRRLLRLRL"));
	}

	//方式一：贪心算法
	public int balancedStringSplit(String s) {
		char[] str = s.toCharArray();
		int l = 0;
		int r = 0;
		int count = 0;
		for (char ch : str) {
			if (ch == 'L') l++;
			if (ch == 'R') r++;
			if (l != 0 && r != 0 && l == r) {
				count++;
				l = 0;
				r = 0;
			}
		}
		return count;
	}

	//方式二：栈方式
	public int balancedStringSplit1(String s) {
		Deque<Character> stack = new LinkedList<>();
		int count = 0;
		for (char ch : s.toCharArray()) {
			if (stack.isEmpty() || stack.peek() == ch) {
				stack.push(ch);
			} else {
				stack.pop();
			}
			if (stack.isEmpty()) count++;
		}
		return count;
	}
}
