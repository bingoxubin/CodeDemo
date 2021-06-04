package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/2 12:57 下午
 */
public class ReOrderList {
	//NC2 重排链表
	//样例:{10,20,30,40}，将其重新排序为{10,40,20,30}.
	//分析：给定一个链表，将链表进行重新排序
	//思路：先折半，然后后面一个链表翻转，然后后面一个插入到前面一个链表的后面
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;

		reorderList(node1);
		while (node1 != null) {
			System.out.print(node1.val + " ");
			node1 = node1.next;
		}
	}

	public static void reorderList(ListNode head) {
		if (head == null) {
			return;
		}
		ListNode mid = getMid(head);
		ListNode phead = reverseNode(mid.next);
		mid.next = null;
		getMerge(head, phead);
	}

	//取中点
	public static ListNode getMid(ListNode head) {
		ListNode first = head;
		ListNode second = head;
		while (second.next != null && second.next.next != null) {
			first = first.next;
			second = second.next.next;
		}
		return first;
	}

	//翻转链表
	public static ListNode reverseNode(ListNode head) {
		ListNode pre = null;
		while (head != null) {
			ListNode tmp = head.next;
			head.next = pre;
			pre = head;
			head = tmp;
		}
		return pre;
	}

	//合并两个链表
	public static void getMerge(ListNode head1, ListNode head2) {
		ListNode node1;
		ListNode node2;
		while (head1 != null && head2 != null) {
			node1 = head1.next;
			node2 = head2.next;

			head1.next = head2;
			head1 = node1;

			head2.next = head1;
			head2 = node2;
		}
	}
}
