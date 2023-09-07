package com.bingoabin.acwing._02structure;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/6/5 15:52
 * @Description: 双链表
 */
public class _02DoubleList {
	public static int N = 100010;
	public static int[] e = new int[N];
	public static int[] l = new int[N];
	public static int[] r = new int[N];
	public static int idx;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		r[0] = 1;
		l[1] = 0;
		idx = 2;

		for (int i = 0; i < n; i++) {
			String flag = sc.next();
			if (flag.equals("L")) {
				int x = sc.nextInt();
				add_all(0, x);
			} else if (flag.equals("R")) {
				int x = sc.nextInt();
				add_all(l[1], x);
			} else if (flag.equals("D")) {
				int k = sc.nextInt();
				remove(k + 1);
			} else if (flag.equals("IR")) {
				int k = sc.nextInt();
				int x = sc.nextInt();
				add_all(k + 1, x);
			} else if (flag.equals("IL")) {
				int k = sc.nextInt();
				int x = sc.nextInt();
				add_all(l[k + 1], x);
			}
		}

		for (int i = r[0]; i != 1; i = r[i]) {
			System.out.println(e[i] + " ");
		}
	}

	public static void remove(int k) {
		r[l[k]] = r[k];
		l[r[k]] = l[k];
	}

	public static void add_all(int k, int x) {
		e[idx] = x;
		r[idx] = r[k];
		l[idx] = k;
		l[r[k]] = idx;
		r[k] = idx++;
	}
}
