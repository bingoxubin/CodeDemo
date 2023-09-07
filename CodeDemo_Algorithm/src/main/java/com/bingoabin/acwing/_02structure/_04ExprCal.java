package com.bingoabin.acwing._02structure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author bingoabin
 * @date 2023/6/7 9:20
 * @Description: 表达式求值
 */
public class _04ExprCal {
	public static void main(String[] args) {
		System.out.println(calculate("1 * 2 + 3 * 4 + 5 * 6"));
	}

	public static int calculate(String s) {
		Deque<Character> q = new LinkedList<>();
		for (char c : s.toCharArray()) {
			q.offer(c);
		}
		return dfs(q);
	}

	public static int dfs(Deque<Character> q) {
		Stack<Integer> stack = new Stack<>();
		int num = 0;
		char op = '+';
		while (!q.isEmpty()) {
			char ch = q.poll();
			if (Character.isDigit(ch)) {
				num = 10 * num + (ch - '0');
			}

			if (ch == '(') {
				num = dfs(q);
			}

			if ((!Character.isDigit(ch) && ch != ' ') || q.isEmpty()) {
				switch (op) {
					case '+':
						stack.push(num);
						break;
					case '-':
						stack.push(-num);
						break;
					case '*':
						stack.push(stack.pop() * num);
						break;
					case '/':
						stack.push(stack.pop() / num);
						break;
				}
				num = 0;
				op = ch;
			}

			if (ch == ')') {
				break;
			}
		}

		System.out.println(stack);

		int res = 0;
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		return res;
	}
}
