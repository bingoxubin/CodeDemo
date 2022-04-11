package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/1 12:53 下午
 */
public class LastKthNode {
	//NC69 链表中倒数第k个节点
	//样例：1,2,3,4,5 k = 2 返回 4
	//分析：输入一个链表，输出该链表中倒数第k个结点。如果该链表长度小于k，请返回空。
	//思路：方式一：先统计长度，判断是否在长度范围内,然后再一个节点先走k，然后一个节点开始走，当先走的到null的时候，后面那个就是倒数第k个
	//     方式二：双指针法
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

		System.out.println(FindKthToTail(node1, 2).val);
	}

	//方式一：先统计长度，判断是否在长度范围内,然后再一个节点先走k，然后一个节点开始走，当先走的到null的时候，后面那个就是倒数第k个
	public static ListNode FindKthToTail(ListNode head, int k) {
		int len = getLen(head);
		if (k > len) {
			return null;
		}
		ListNode first = head;
		ListNode second = head;
		//先走k个位置
		for (int i = 0; i < k; i++) {
			first = first.next;
		}
		//然后一起走
		while (first != null) {
			first = first.next;
			second = second.next;
		}
		return second;
	}

	//统计链表长度
	public static int getLen(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}

	//方式二：双指针法，定义快慢两个节点，快的先走k步，然后让一起走，走到最终慢的那个节点就是了
	public ListNode getKthFromEnd2(ListNode head, int k) {
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && k > 0) {
			fast = fast.next;
			k--;
		}
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}

		return slow;
	}

	//方式三：计算长度的简便写法
	public ListNode getKthFromEnd3(ListNode head, int k) {
		int n = 0;
		ListNode node = null;

		for (node = head; node != null; node = node.next) {
			n++;
		}
		for (node = head; n > k; n--) {
			node = node.next;
		}

		return node;
	}

	//自己写一遍
	public ListNode getKthFromEnd4(ListNode head, int k) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && k > 0) {
			fast = fast.next;
			k--;
		}
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}
}
