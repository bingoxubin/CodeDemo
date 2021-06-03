package com.bingoabin.algorithm.listnode;

import scala.util.control.TailCalls;

import javax.naming.directory.SearchControls;

/**
 * @author xubin03
 * @date 2021/6/2 12:43 下午
 */
public class OddEvenReorder {
	//NC133 链表的奇偶重排
	//样例：{1,2,3,4,5,6} 返回：{1,3,5,2,4,6}
	//分析：给一个链表，进行奇数位置偶数位置重新排列 奇数位置放左边 偶数位置放右边
	//思路：
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

		ListNode res = oddEvenList(node1);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}

	//先标记第二个元素 ，因为这个是后面一个链表的开始，然后用first指向第一个，second指向第二个，进行调整顺序
	public static ListNode oddEvenList(ListNode head) {
		if (head == null) return head;
		ListNode tail = head.next;
		ListNode first = head;
		ListNode second = head.next;
		while (second != null && second.next != null) {
			first.next = second.next;
			first = first.next;
			second.next = first.next;
			second = second.next;
		}
		first.next = tail;
		return head;
	}
}
