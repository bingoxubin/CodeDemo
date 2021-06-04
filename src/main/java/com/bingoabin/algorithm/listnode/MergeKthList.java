package com.bingoabin.algorithm.listnode;

import com.mysql.jdbc.interceptors.ResultSetScannerInterceptor;

import java.util.ArrayList;

/**
 * @author xubin03
 * @date 2021/6/4 11:03 上午
 */
public class MergeKthList {
	//NC51 合并k个已排序的链表
	//样例：一条链表：1 6 9 12 15  另一条链表：2，3，10，13，18  返回 1，2，3，6，9，10.12.13.15.18
	//分析：将k个有序链表进行合并
	//思路：方式一：迭代，一个个合并
	//     方式二：采用优先队列
	//     方式三：两两合并  迭代
	//     方式四：两辆合并  递归
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(6);
		ListNode node3 = new ListNode(9);
		ListNode node4 = new ListNode(12);
		ListNode node5 = new ListNode(15);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		ListNode node11 = new ListNode(2);
		ListNode node22 = new ListNode(3);
		ListNode node33 = new ListNode(10);
		ListNode node44 = new ListNode(13);
		ListNode node55 = new ListNode(18);
		node11.next = node22;
		node22.next = node33;
		node33.next = node44;
		node44.next = node55;

		ArrayList<ListNode> lists = new ArrayList<>();
		lists.add(node1);
		lists.add(node11);

		ListNode res = mergeKLists1(lists);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}

	//方式一：迭代 定义一个哑节点做头部，后面每次在list里面找一个节点放到亚节点后面
	public static ListNode mergeKLists1(ArrayList<ListNode> lists) {
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (true) {
			ListNode temp = null;
			int flag = -1;
			for (int i = 0; i < lists.size(); i++) {
				if (lists.get(i) == null) {
					continue;
				}
				if (temp == null || lists.get(i).val < temp.val) {
					temp = lists.get(i);
					flag = i;
				}
			}
			if (flag == -1) {
				break;
			}
			res.next = temp;
			res = res.next;
			lists.set(flag, lists.get(flag).next);
		}
		return dummy.next;
	}

	//方式二：优先队列
	public static ListNode mergeKLists2(ArrayList<ListNode> lists) {

		return null;
	}
}
