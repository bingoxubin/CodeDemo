package com.bingoabin.leetcode;

/**
 * @author bingoabin
 * @date 2023/5/8 22:42
 * @Description:
 */
public class _0002AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		int temp = 0;
		while (l1 != null || l2 != null || temp != 0) {
			if (l1 != null) {
				temp += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				temp += l2.val;
				l2 = l2.next;
			}
			res.next = new ListNode(temp % 10);
			res = res.next;
			temp /= 10;
		}
		return dummy.next;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
