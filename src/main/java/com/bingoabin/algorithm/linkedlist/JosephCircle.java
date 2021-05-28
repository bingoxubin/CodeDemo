package com.bingoabin.algorithm.linkedlist;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;

/**
 * @author xubin03
 * @date 2021/5/25 5:19 下午
 */
public class JosephCircle {
	//NC132 环形链表的约瑟夫问题
	//样例：5，2  输出 3
	//分析：给你2个数字，比如5，2，代表1 2 3 4 5形成一个环，从1开始报数，报数2就自杀，最后剩下谁；第一次1345，135，35，3
	//思路：方式一：创建一个arraylist进行模拟，不断的进行删除数据，删除的下标为 (m - 1 + index) % list.size()
	//     方式二：进行倒推的方式，比如最后活着的肯定在0下标上，如果上一轮要活下来，那么它还是在0坐标上，
	public static void main(String[] args) {
		System.out.println(ysf(5, 2));
		System.out.println(ysf1(5, 2));
	}

	//方式一：创建arraylist进行模拟    1 2 3 4 5  第一个去掉下标为1  1345接下来去掉下标为2  135接下来去掉下表为0   35接下来去掉下表为1
	public static int ysf(int n, int m) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		int index = 0;
		while (list.size() > 1) {
			index = (m + index - 1) % list.size();
			list.remove(index);
		}
		return list.get(0);
	}

	//方式二：通过数学的方式进行逆向推到
	//  1 2 3 4 5
	//  3 4 5 1         #删掉了2
	//  5 1 3           #删掉了4
	//  3 5             #删掉了1
	//  3               #删掉了5
	public static int ysf1(int n, int m) {
		int res = 0;
		for (int i = 2; i <= n; i++) {
			res = (res + m) % i;
		}
		return res + 1;
	}
}
