package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/5/30 3:21 上午
 */
public class ReverseList {
	//NC78 反转链表
	//样例：1,2,3,4,5  返回 5 4 3 2 1
	//分析：给你一个链表，进行翻转后返回
	//思路：方式一：迭代的方式，定义一个pre为null，然后进行不断向后遍历，每次遍历到一个节点  翻转指针
	//      方式二：递归的方式，通过递归到最后一个节点，然后从后面不断往前进行递归
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
		ListNode res1 = ReverseList1(node1);
		while (res1 != null) {
			System.out.print(res1.val + " ");
			res1 = res1.next;
		}

		System.out.println();

		ListNode node11 = new ListNode(1);
		ListNode node22 = new ListNode(2);
		ListNode node33 = new ListNode(3);
		ListNode node44 = new ListNode(4);
		ListNode node55 = new ListNode(5);
		node11.next = node22;
		node22.next = node33;
		node33.next = node44;
		node44.next = node55;
		ListNode res2 = ReverseList2(node11);
		while (res2 != null) {
			System.out.print(res2.val + " ");
			res2 = res2.next;
		}
	}

	//方式一：迭代的方式，定义一个pre为null，然后进行不断向后遍历，每次遍历到一个节点  翻转指针
	public static ListNode ReverseList1(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode pre = null;
		while (head != null) {
			ListNode temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		return pre;
	}

	//方式二：递归的方式，通过递归到最后一个节点，然后从后面不断往前进行递归
	public static ListNode ReverseList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode res = ReverseList2(head.next);
		head.next.next = head;
		head.next = null;
		return res;
	}

	//方式一：递归
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode res = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return res;
	}

	//方式二：迭代
	public static ListNode reverseList3(ListNode head) {
		if (head == null) return head;
		ListNode pre = null;
		while (head != null) {
			ListNode temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		return pre;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}
}
