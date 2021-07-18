package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/1 12:53 下午
 */
public class LastKthNode {
	//NC69 链表中倒数第k个节点
	//样例：1,2,3,4,5 k = 2 返回 4
	//分析：输入一个链表，输出该链表中倒数第k个结点。如果该链表长度小于k，请返回空。
	//思路：先统计长度，判断是否在长度范围内,然后再一个节点先走k，然后一个节点开始走，当先走的到null的时候，后面那个就是倒数第k个
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

		System.out.println(FindKthToTail(node1, 2).val);
	}

	//先统计长度，判断是否在长度范围内,然后再一个节点先走k，然后一个节点开始走，当先走的到null的时候，后面那个就是倒数第k个
	public static ListNode FindKthToTail(ListNode head, int k) {
		int len = getLen(head);
		if (k > len) {
			return null;
		}
		ListNode first = head;
		ListNode second = head;
		//先走k个位置
		for (int i = 0; i < k; i++) {
			first = first.next;
		}
		//然后一起走
		while (first != null) {
			first = first.next;
			second = second.next;
		}
		return second;
	}

	//统计链表长度
	public static int getLen(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}
}
