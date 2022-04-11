package com.bingoabin.algorithm.listnode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/6/5 1:07 下午
 */
public class FIrstCommonListNode {
	//NC66 两个链表的第一个公共节点
	//样例：1→2→3→4→5, 6->3->4->5 返回 3这个节点
	//分析：给两个链表，返回两个链表的相交节点
	//思路：两个节点分别从头开始，如果一个到头了，那么从另一个链表的头开始，不断循环下去
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

		ListNode node6 = new ListNode(6);
		node6.next = node3;

		System.out.println(FindFirstCommonNode3(node1, node6).val);
	}

	//方式一：计算长度，两个长度之差，然后长的那个先走他们的长度之差，然后再一起走，直到相同
	public static ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
		int len1 = getLen(pHead1);
		int len2 = getLen(pHead2);
		if (len1 > len2) {
			int k = len1 - len2;
			while (k > 0) {
				pHead1 = pHead1.next;
				k--;
			}
		} else {
			int k = len2 - len1;
			while (k > 0) {
				pHead2 = pHead2.next;
				k--;
			}
		}
		while (pHead1 != pHead2) {
			pHead1 = pHead1.next;
			pHead2 = pHead2.next;
		}
		return pHead1;
	}

	public static int getLen(ListNode node) {
		int len = 0;
		while (node != null) {
			len++;
			node = node.next;
		}
		return len;
	}

	//方式二：双指针 两个节点分别从头开始，如果一个到头了，那么从另一个链表的头开始，不断循环下去
	public static ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
		ListNode phead1 = pHead1;
		ListNode phead2 = pHead2;
		while (phead1 != phead2) {
			phead1 = phead1 == null ? pHead2 : phead1.next;
			phead2 = phead2 == null ? pHead1 : phead2.next;
		}
		return phead1;
	}

	//方式三：用栈的方式
	public static ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {
		Deque<ListNode> stack1 = new LinkedList<>();
		Deque<ListNode> stack2 = new LinkedList<>();
		while (pHead1 != null) {
			stack1.offerLast(pHead1);
			pHead1 = pHead1.next;
		}
		while (pHead2 != null) {
			stack2.offerLast(pHead2);
			pHead2 = pHead2.next;
		}
		ListNode res = null;
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			ListNode p1 = stack1.pollLast();
			ListNode p2 = stack2.pollLast();
			if (p1 == p2) {
				res = p1;
			} else {
				break;
			}
		}
		return res;
	}
}
