//反转列表
//输入一个链表，反转链表后，输出新链表的表头。

//思路：
//本题的关键就是在于对next域的赋值，同时对下一个节点进行保存，然后对把下一个节点赋给新的节点，这样依次循环完所有的节点。每次使新插入的节点变成头第一个有效节点

package com.bingoabin.newcoder;

public class _15ReverseList {
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public ListNode ReverseList(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}
			ListNode temp = head.next;
			ListNode result = ReverseList(head.next);
			temp.next = head;
			head.next = null;
			return result;
		}
	}

	public class Solution1 {
		public ListNode ReverseList(ListNode head) {
			ListNode pre = null;
			ListNode next = null;
			while (head != null) {
				next = head.next;//next前移
				head.next = pre;//倒置
				pre = head;//pre前移
				head = next;//head前移
			}
			return pre;
		}
	}
}
