package com.bingoabin.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/5/26 1:35 上午
 */
public class ReversePairString {
	//LeetCode 1190. 反转每对括号间的子串  https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
	//样例："(ed(et(oc))el)"  返回 "leetcode"
	//分析：给一个字符串，按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
	//思路：用栈的方式，首先先将字符拼接起来，碰到"("将前面累加的string 放到栈中，string清空，然后继续累加，碰到")",将字符串翻转，然后将栈里面的取出来放到翻转的字符串前面
	//(ed(et(oc))el) ->(ed(etco)el) -> ed octe el -> leetcode
	public static void main(String[] args) {
		String str = "(ed(et(oc))el)";
		System.out.println(reverseParentheses(str));
	}

	public static String reverseParentheses(String s) {
		StringBuilder res = new StringBuilder();
		Deque<String> stack = new LinkedList<String>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				//如果是（  将收集到的 string 加入到队列中，然后清空sring
				stack.push(res.toString());
				res.setLength(0);
			} else if (ch == ')') {
				//如果是) 将收集到的string翻转，然后将stack中的弹出放到string前面
				res.reverse();
				res.insert(0, stack.pop());
			} else {
				res.append(ch);
			}
		}
		return res.toString();
	}
}
