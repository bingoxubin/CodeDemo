package com.bingoabin.newcoder;

//删除链表中重复的节点
//在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
public class _56DeleteListNode {
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public ListNode deleteDuplication(ListNode pHead) {
			ListNode result;
			ListNode temp = pHead;
			ListNode index = new ListNode(1);
			index.next = pHead;
			result = index;
			while (temp != null) {
				if (temp.next != null && temp.next.val == temp.val) {
					while (temp.next != null && temp.next.val == temp.val) {
						temp = temp.next;
					}
					temp = temp.next;
					index.next = temp;
				} else {
					index = index.next;
					temp = temp.next;
				}
			}
			return result.next;
		}
	}

	public class Solution1 {
		public ListNode deleteDuplication(ListNode pHead) {

			if (pHead == null) {
				return null;
			}
			ListNode p = pHead;
			ListNode n = new ListNode(0);
			ListNode pre = n;
			n.next = pHead;
			boolean flag = false;
			while (p != null) {
				ListNode q = p.next;
				if (q == null) {
					break;
				}
				if (q.val == p.val) {
					while (q != null && q.val == p.val) {
						q = q.next;
					}
					pre.next = q;
					p = q;
				} else {
					if (!flag) {
						n.next = p;
						flag = true;
					}
					pre = p;
					p = q;
				}
			}
			return n.next;
		}
	}
}
