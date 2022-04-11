package com.bingoabin.algorithm.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/6/5 11:08 下午
 */
public class BracketSequence {
	//NC52 括号序列
	//样例：输入"((({{[[]]}})))" 输出：true
	//分析：给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
	//思路:用栈的思想+if判断  或者使用栈+hashmap
	public static void main(String[] args) {
		String str = "((({{[[]]}})))";
		String str1 = "([)]";
		System.out.println(isValid2(str1));
	}

	//方式一：栈+if判断
	public static boolean isValid1(String s) {
		if (s.length() % 2 == 1) {
			return false;
		}
		Deque<Character> stack = new LinkedList<>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char temp = chars[i];
			if (temp == '(') {
				stack.push(')');
			} else if (temp == '{') {
				stack.push('}');
			} else if (temp == '[') {
				stack.push(']');
			} else if (!stack.isEmpty() && stack.pop() != temp) {
				return false;
			}
		}
		return stack.isEmpty();
	}

	//方式二：栈+hashmap
	public static boolean isValid2(String s) {
		HashMap<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');
		Deque<Character> stack = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!stack.isEmpty() && stack.peek().equals(map.get(ch))) {
				stack.pop();
			} else {
				stack.push(ch);
			}
		}
		return stack.isEmpty();
	}
}
