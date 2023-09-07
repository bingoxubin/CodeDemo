package com.bingoabin.acwing._02structure;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/6/5 15:13
 * @Description: 单链表
 */

//输入样例：
// 10
// H 9
// I 1 1
// D 1
// D 0
// H 6
// I 3 6
// I 4 5
// I 4 5
// I 3 4
// D 6
// 输出样例：
// 6 4 6 5
public class _01SingleList {
	public static int N = 100010;
	//链表中对应下标节点的值
	public static int[] e = new int[N];
	//链表下一个next的对应的下标
	public static int[] ne = new int[N];
	//头节点的下标值
	public static int head = -1;
	//下一个节点该放的位置
	public static int idx = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			String flag = sc.next();
			if (flag.equals("H")) {
				int x = sc.nextInt();
				addHead(x);
			} else if (flag.equals("I")) {
				int k = sc.nextInt();
				int x = sc.nextInt();
				insert(k - 1, x);
			} else if (flag.equals("D")) {
				int k = sc.nextInt();
				if (k == 0) {
					head = ne[head];
				} else {
					delete(k - 1);
				}
			}
		}
	}

	public static void insert(int k, int x) {
		e[idx] = x;
		ne[idx] = ne[k];
		ne[k] = idx;
		idx++;
	}

	public static void addHead(int x) {
		e[idx] = x;
		ne[idx] = head;
		head = idx;
		idx++;
	}

	public static void delete(int k) {
		ne[k] = ne[ne[k]];
	}
}
