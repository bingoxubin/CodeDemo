package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/4 12:29 上午
 */
public class CrossPointListNode {
	//LeetCode 160. 相交链表 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
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

		System.out.println(getIntersectionNode(node1, node6).val);
	}

	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode heada = headA;
		ListNode headb = headB;
		while (heada != headb) {
			heada = heada == null ? headB : heada.next;
			headb = headb == null ? headA : headb.next;
		}
		return heada;
	}
}
