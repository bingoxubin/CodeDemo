package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/1 5:42 下午
 */
public class IsPailListNode {
	//NC96判断一个链表是否回文结构
	//样例：1,2,3,4,5,4,3,2,1 返回 true
	//分析：给一个链表，判断是否具有回文结构
	//思路：先获取链表的中间节点，然后拆开成两个链表，将后面一个链表翻转，两个链表同时走，看数据是否一致，不一致返回false
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(4);
		ListNode node7 = new ListNode(3);
		ListNode node8 = new ListNode(2);
		ListNode node9 = new ListNode(1);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		node8.next = node9;

		System.out.println(isPail(node1));
	}

	//先获取链表的中间节点，然后拆开成两个链表，将后面一个链表翻转，两个链表同时走，看数据是否一致，不一致返回false；
	public static boolean isPail(ListNode head) {
		ListNode mid = getMid(head);
		ListNode temp = mid.next;
		mid.next = null;
		ListNode phead = reverseList1(temp);
		while (phead != null) {
			if (phead.val != head.val) return false;
			phead = phead.next;
			head = head.next;
		}
		return true;
	}

	//取中点
	public static ListNode getMid(ListNode head) {
		ListNode first = head;
		ListNode second = head;
		while (first != null && first.next != null) {
			first = first.next.next;
			second = second.next;
		}
		return second;
	}

	//翻转 方式一
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode res = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return res;
	}

	//翻转  方式二
	public static ListNode reverseList1(ListNode head) {
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
