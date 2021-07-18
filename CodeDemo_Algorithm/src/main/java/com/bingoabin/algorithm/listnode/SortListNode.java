package com.bingoabin.algorithm.listnode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author xubin03
 * @date 2021/6/4 10:05 上午
 */
public class SortListNode {
	//NC70 单链表排序
	//样例：1,5,4,3,2 返回 1,2,3,4,5
	//分析：给你一个链表，将链表进行排序，然后返回
	//思路：方式一：通过辅助arraylist,放到arraylist然后排序，然后串联
	//     方式二：在原链表上进行排序，递归将链表进行拆分，拆到最细粒度，然后进行合并
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(5);
		ListNode node3 = new ListNode(4);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(2);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		// ListNode res1 = sortInList1(node1);
		// while (res1 != null) {
		// 	System.out.print(res1.val + " ");
		// 	res1 = res1.next;
		// }

		ListNode res2 = sortInList2(node1);
		while (res2 != null) {
			System.out.print(res2.val + " ");
			res2 = res2.next;
		}
	}

	//方式一：通过辅助arraylist,放到arraylist然后排序，然后串联
	public static ListNode sortInList1(ListNode head) {
		ArrayList<Integer> list = new ArrayList<>();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}
		Collections.sort(list);
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		for (Integer val : list) {
			res.next = new ListNode(val);
			res = res.next;
		}
		return dummy.next;
	}

	//方式二：在原链表上进行排序，递归将链表进行拆分，拆到最细粒度，然后进行合并
	public static ListNode sortInList2(ListNode head) {
		//拆开 返回条件
		if (head == null || head.next == null) {
			return head;
		}
		//找中点
		ListNode first = head;
		ListNode second = head;
		while (second.next != null && second.next.next != null) {
			first = first.next;
			second = second.next.next;
		}
		ListNode p2head = sortInList2(first.next);
		first.next = null;
		ListNode p1head = sortInList2(head);
		return merge1(p1head, p2head);
	}

	//合并的方式一 递归
	public static ListNode merge1(ListNode node1, ListNode node2) {
		if (node1 == null) {
			return node2;
		}
		if (node2 == null) {
			return node1;
		}
		if (node1.val < node2.val) {
			node1.next = merge1(node1.next, node2);
			return node1;
		}
		node2.next = merge2(node1, node2.next);
		return node2;
	}

	//合并的方式二 迭代
	public static ListNode merge2(ListNode node1, ListNode node2) {
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (node1 != null && node2 != null) {
			if (node1.val < node2.val) {
				res.next = node1;
				node1 = node1.next;
			} else {
				res.next = node2;
				node2 = node2.next;
			}
			res = res.next;
		}
		res.next = node1 == null ? node2 : node1;
		return dummy.next;
	}
}
