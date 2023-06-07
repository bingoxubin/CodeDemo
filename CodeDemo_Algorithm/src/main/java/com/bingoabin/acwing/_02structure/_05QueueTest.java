package com.bingoabin.acwing._02structure;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/6/7 9:34
 * @Description: 模拟队列
 */
public class _05QueueTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] res = new int[100010];
		int head = 0;
		int tail = -1;
		for (int i = 0; i < n; i++) {
			String op = sc.next();
			if (op.equals("push")) {
				int x = sc.nextInt();
				res[++tail] = x;
			} else if (op.equals("pop")) {
				head++;
			} else if (op.equals("empty")) {
				if (head <= tail) {
					System.out.println("NO");
				} else {
					System.out.println("YES");
				}
			} else {
				System.out.println(res[head]);
			}
		}
	}
}
