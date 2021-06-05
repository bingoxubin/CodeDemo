package com.bingoabin.algorithm.listnode;

import jdk.nashorn.internal.ir.ReturnNode;

/**
 * @author xubin03
 * @date 2021/6/5 4:06 下午
 */
public class TwoListADD {
	//NC40 两个链表生成相加链表
	//样例：一条链表：1 2 3 4 5  另一条链表：2，3，4，5，6  返回      3 5 8 0 1
	//分析：将两个链表进行相加，从后往前进位
	//思路：首先将两个链表进行翻转，然后相加
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		ListNode node11 = new ListNode(2);
		ListNode node22 = new ListNode(3);
		ListNode node33 = new ListNode(4);
		ListNode node44 = new ListNode(5);
		ListNode node55 = new ListNode(6);
		node11.next = node22;
		node22.next = node33;
		node33.next = node44;
		node44.next = node55;

		ListNode res = addInList(node1, node11);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}

	public static ListNode addInList(ListNode head1, ListNode head2) {
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		ListNode node1 = reverseIter(head1);
		ListNode node2 = reverseIter(head2);
		int flag = 0;
		while (node1 != null || node2 != null) {
			int num1 = node1 == null ? 0 : node1.val;
			int num2 = node2 == null ? 0 : node2.val;
			int sum = flag + num1 + num2;
			flag = sum / 10;
			res.next = new ListNode(sum % 10);
			res = res.next;
			if (node1 != null) {
				node1 = node1.next;
			}
			if (node2 != null) {
				node2 = node2.next;
			}
		}
		if (flag > 0) {
			res.next = new ListNode(flag);
		}
		ListNode ans = dummy.next;
		dummy.next = null;
		return reverseIter(ans);
	}

	//翻转  方式一：递归
	public static ListNode reverseRecur(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}
		ListNode res = reverseRecur(node.next);
		node.next.next = node;
		node.next = null;
		return res;
	}

	//翻转 方式二：迭代
	public static ListNode reverseIter(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}
		ListNode pre = null;
		while (node != null) {
			ListNode temp = node.next;
			node.next = pre;
			pre = node;
			node = temp;
		}
		return pre;
	}
}
