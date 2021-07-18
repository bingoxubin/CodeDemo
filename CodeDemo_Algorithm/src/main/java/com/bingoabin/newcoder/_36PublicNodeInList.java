//两个链表的第一个公共节点
//输入两个链表，找出它们的第一个公共结点。

//公共节点指的是从该节点到尾节点都相同（因为一个节点只能有一个后继）
//         1.将两个链表分别压人栈stack1和stack2中。
//         2.当stack1与stack2皆不为空时，每次各从stack1和stack2取出一个元素，若相等则继续出栈判断，若不等则跳出循环。

//找出2个链表的长度，然后让长的先走两个链表的长度差，然后再一起走（因为2个链表用公共的尾部）

package com.bingoabin.newcoder;

import java.util.Stack;

public class _36PublicNodeInList {

	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
			if (pHead1 == null || pHead2 == null) {
				return null;
			}
			int count1 = 0;
			ListNode p1 = pHead1;
			while (p1 != null) {
				p1 = p1.next;
				count1++;
			}
			int count2 = 0;
			ListNode p2 = pHead2;
			while (p2 != null) {
				p2 = p2.next;
				count2++;
			}
			int flag = count1 - count2;
			if (flag > 0) {
				while (flag > 0) {
					pHead1 = pHead1.next;
					flag--;
				}
				while (pHead1 != pHead2) {
					pHead1 = pHead1.next;
					pHead2 = pHead2.next;
				}
				return pHead1;
			}
			if (flag <= 0) {
				while (flag < 0) {
					pHead2 = pHead2.next;
					flag++;
				}
				while (pHead1 != pHead2) {
					pHead2 = pHead2.next;
					pHead1 = pHead1.next;
				}
				return pHead1;
			}
			return null;
		}
	}

	public class Solution1 {
		public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
			Stack<ListNode> stack1 = new Stack<ListNode>();
			Stack<ListNode> stack2 = new Stack<ListNode>();
			ListNode p1 = null, p2 = null, result = null;
			while (pHead1 != null) {
				stack1.push(pHead1);
				pHead1 = pHead1.next;
			}
			while (pHead2 != null) {
				stack2.push(pHead2);
				pHead2 = pHead2.next;
			}
			while (!stack1.empty() && !stack2.empty()) {
				p1 = stack1.pop();
				p2 = stack2.pop();
				if (p1 == p2) {
					result = p1;
				} else {
					break;
				}
			}
			return result;
		}
	}

	public class Solution2 {
		public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
			ListNode phead1 = pHead1;
			ListNode phead2 = pHead2;
			int length1 = getListLength(pHead1);
			int length2 = getListLength(pHead2);
			if (length1 >= length2) {
				int k = length1 - length2;
				while (k > 0) {
					phead1 = phead1.next;
					k--;
				}
			} else {
				int k = length2 - length1;
				while (k > 0) {
					phead2 = phead2.next;
					k--;
				}
			}
			while (phead1 != phead2) {
				phead1 = phead1.next;
				phead2 = phead2.next;
			}
			return phead1;
		}

		public int getListLength(ListNode head) {
			int length = 0;
			while (head != null) {
				length++;
				head = head.next;
			}
			return length;
		}
	}
}
