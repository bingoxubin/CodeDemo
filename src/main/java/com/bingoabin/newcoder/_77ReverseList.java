package com.bingoabin.newcoder;

/**
 * @author xumaosheng
 * 如何反转单链表
 * @date 2019/12/19 17:15
 */
public class _77ReverseList {
	public static ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
