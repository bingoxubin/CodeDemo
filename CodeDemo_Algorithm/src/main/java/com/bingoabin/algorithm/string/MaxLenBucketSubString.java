package com.bingoabin.algorithm.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/6/10 2:16 上午
 */
public class MaxLenBucketSubString {
	//NC49 最长的括号子串
	//样例：输入")()())" 输出：4
	//分析：仅包含字符'('和')'的字符串，计算最长的格式正确的括号子串的长度
	//思路：方式一：通过栈的方式，定义一个栈，然后将"（"的下标放到栈中，碰到"）"如果栈为空，则加入，否则的话计算长度i- stack.peek()
	//     方式二：无需额外空间,从左到右计算一遍，（，left++,)right++  如果left == right 可以统计结果，如果right > left 则left right 重新归0  从右边到左边再计算一次
	public static void main(String[] args) {
		String str = ")()()";
		System.out.println(longestValidParentheses1(str));
		System.out.println(longestValidParentheses2(str));
		System.out.println(longestValidParentheses3(str));
	}

	//方式一：通过栈的方式，定义一个栈，然后将"（"的下标放到栈中，碰到"）"如果栈为空，则加入，否则的话计算长度i- stack.peek()
	public static int longestValidParentheses1(String s) {
		Deque<Integer> stack = new LinkedList<Integer>();
		int res = 0;
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					res = Math.max(res, i - stack.peek());
				}
			}
		}
		return res;
	}

	//方式二：无需额外空间,从左到右计算一遍，（，left++,)right++  如果left == right 可以统计结果，如果right > left 则left right 重新归0  从右边到左边再计算一次
	public static int longestValidParentheses2(String s) {
		int left = 0, right = 0, maxlength = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * right);
			} else if (right > left) {
				left = right = 0;
			}
		}
		left = right = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * left);
			} else if (left > right) {
				left = right = 0;
			}
		}
		return maxlength;
	}

	//方式三：动态规划
	public static int longestValidParentheses3(String s) {
		int maxans = 0;
		int[] dp = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
				maxans = Math.max(maxans, dp[i]);
			}
		}
		return maxans;
	}
}
