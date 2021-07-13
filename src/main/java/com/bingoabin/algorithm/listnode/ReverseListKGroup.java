package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/5 4:27 下午
 */
public class ReverseListKGroup {
	//NC50 链表中的节点每k个一组翻转
	//样例：1,2,3,4,5   2 返回 21435
	//分析：给你一个链表，按照k个一组进行翻转
	//思路：定义两个亚节点，一个停留在组的头部，一个停留在组的尾部，进行翻转后串联，然后循环下一步
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
		ListNode res1 = reverseKGroup(node1, 2);
		while (res1 != null) {
			System.out.print(res1.val + " ");
			res1 = res1.next;
		}
	}

	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode start = dummy;
		ListNode end = dummy;
		while (start != null) {
			for (int i = 0; i < k && end != null; i++) {
				end = end.next;
			}
			if (end == null) {
				break;
			}
			ListNode pre = start.next;
			ListNode last = end.next; // 0 1 2  0是start 2是end 1是pre 3是last
			end.next = null;
			start.next = reverseIter(pre);
			pre.next = last;
			start = pre;
			end = pre;
		}
		return dummy.next;
	}

	//翻转 方式一：迭代
	public static ListNode reverseIter(ListNode node) {
		if (node == null || node.next == null) return node;
		ListNode pre = null;
		while (node != null) {
			ListNode temp = node.next;
			node.next = pre;
			pre = node;
			node = temp;
		}
		return pre;
	}

	//翻转 方式二：递归
	public static ListNode reverseRecur(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}
		ListNode res = reverseRecur(node.next);
		node.next.next = node;
		node.next = null;
		return res;
	}
}
