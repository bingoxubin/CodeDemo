package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/4 12:35 上午
 */
public class DivideListNode {
	//NC23 划分链表
	//样例：1 4 3 2 5 2 和数字 3  返回 1 2 2 4 3 5
	//分析：将链表小于目标数字的放在左边，大于等于目标值的放到右边
	//思路：定义两个头节点 亚节点  小于目标值的跟在第一个后面，大于等于的跟在后面一个，然后两个合起来
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(4);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(2);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(2);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;

		ListNode res = partition(node1, 3);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}

	public static ListNode partition(ListNode head, int x) {
		ListNode less = new ListNode(-1);
		ListNode more = new ListNode(-1);
		ListNode left = less, right = more;
		while (head != null) {
			if (head.val < x) {
				left.next = head;
				left = left.next;
			} else {
				right.next = head;
				right = right.next;
			}
			head = head.next;
		}
		right.next = null;
		left.next = more.next;
		return less.next;
	}
}
