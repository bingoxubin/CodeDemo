package com.bingoabin.algorithm.listnode;

import java.util.HashSet;

/**
 * @author xubin03
 * @date 2021/5/31 2:05 上午
 */
public class HasCycle {
	//NC4 判断链表是否有环
	//样例：1,2,3,4,5,3 返回 true
	//分析：给你一个链表，判断是否有环
	//思路：方式一：快慢指针，慢的能追上那就是有环
	//     方式二：hashset  如果出现重复的就是有环
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
		node5.next = node3;

		System.out.println(hasCycle1(node1));
		System.out.println(hasCycle2(node1));
	}

	//方式一：快慢指针，慢的能追上那就是有环
	public static boolean hasCycle1(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	//方式二：hashset  如果出现重复的就是有环
	public static boolean hasCycle2(ListNode head) {
		HashSet<ListNode> set = new HashSet<ListNode>();
		while (head != null) {
			if (!set.add(head)) {
				return true;
			}
			head = head.next;
		}
		return false;
	}
}
