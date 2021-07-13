//从尾到头打印链表
//输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。

//思路：
//      采用递归：
// 		若当前节点不为空，则递归其后继节点，并将当前节点加入list中。
//
// 		采用数据结构栈实现：
// 		利用栈“后进先出”特性
//
// 		利用ArrayList的public void add(int index, E element)：
// 		从前往后遍历，每次插在list的最前面位置。

package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.Stack;

public class _03PrintArrayList {
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
			ArrayList<Integer> temp = new ArrayList();
			while (listNode != null) {
				temp.add(listNode.val);
				listNode = listNode.next;
			}
			ArrayList<Integer> result = new ArrayList();
			for (int i = temp.size() - 1; i >= 0; i--) {
				result.add(temp.get(i));
			}
			return result;
		}
	}

	public class Solution1 {
		ArrayList<Integer> list = new ArrayList<Integer>();

		public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
			if (listNode != null) {
				printListFromTailToHead(listNode.next);
				list.add(listNode.val);
			}
			return list;
		}
	}

	public class Solution2 {
		public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
			Stack<ListNode> stack = new Stack<>();
			ArrayList<Integer> list = new ArrayList<>();
			while (listNode != null) {
				stack.push(listNode);
				listNode = listNode.next;
			}

			while (!stack.empty()) {
				list.add(stack.pop().val);
			}
			return list;
		}
	}

	public class Solution3 {
		public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			while (listNode != null) {
				list.add(0, listNode.val);
				listNode = listNode.next;
			}
			return list;
		}
	}

}
