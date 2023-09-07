package com.bingoabin.acwing._02structure;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/6/6 9:24
 * @Description: 模拟栈
 */
public class _03SimulateStack {
	public static int N = 100010;
	public static int[] stack = new int[N];
	public static int idx = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			String op = sc.next();
			switch (op) {
				case "push":
					push(sc.nextInt());
					break;
				case "pop":
					pop();
					break;
				case "empty":
					System.out.println(empty() ? "YES" : "NO");
					break;
				case "query":
					System.out.println(query());
					break;
			}
		}
	}

	public static void push(int x) {
		stack[++idx] = x;
	}

	public static void pop() {
		idx--;
	}

	public static boolean empty() {
		return idx == 0;
	}

	public static int query() {
		return stack[idx];
	}
}
