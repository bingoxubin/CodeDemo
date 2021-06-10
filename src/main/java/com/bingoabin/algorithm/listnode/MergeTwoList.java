package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/5/31 6:36 下午
 */
public class MergeTwoList {
	//NC33 合并有序链表
	//样例：一条链表：1 6 9 12 15  另一条链表：2，3，10，13，18  返回 1，2，3，6，9，10.12.13.15.18
	//分析：将两个有序链表进行合并
	//思路：方式一：迭代的方式进行合并
	//     方式二：递归的方式进行合并
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(6);
		ListNode node3 = new ListNode(9);
		ListNode node4 = new ListNode(12);
		ListNode node5 = new ListNode(15);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		ListNode node11 = new ListNode(2);
		ListNode node22 = new ListNode(3);
		ListNode node33 = new ListNode(10);
		ListNode node44 = new ListNode(13);
		ListNode node55 = new ListNode(18);
		node11.next = node22;
		node22.next = node33;
		node33.next = node44;
		node44.next = node55;

		// ListNode res = mergeTwoLists1(node1, node11);
		// while (res != null) {
		// 	System.out.print(res.val + " ");
		// 	res = res.next;
		// }

		ListNode res1 = mergeTwoLists2(node1, node11);
		while (res1 != null) {
			System.out.print(res1.val + " ");
			res1 = res1.next;
		}
	}

	//方式一：迭代的方式进行合并
	public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				res.next = l1;
				l1 = l1.next;
			} else {
				res.next = l2;
				l2 = l2.next;
			}
			res = res.next;
		}
		res.next = l1 == null ? l2 : l1;
		return dummy.next;
	}

	//方式二：递归的方式进行合并
	public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists2(l1.next, l2);
			return l1;
		}
		l2.next = mergeTwoLists2(l1, l2.next);
		return l2;
	}
}
