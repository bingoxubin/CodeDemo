package com.bingoabin.algorithm.listnode;

import java.util.HashSet;

/**
 * @author xubin03
 * @date 2021/6/3 12:43 下午
 */
public class DeleteRepetitionNodeOne {
	//NC25 删除有序链表中重复的元素(保留一个） https://www.nowcoder.com/practice/c087914fae584da886a0091e877f2c79?tpId=117&&tqId=37730&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：给出的链表为1 1 2 3 3 返回 1 2 3
	//分析：删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
	//思路：方式一：标记链表的头结点，从head开始，如果head的值跟head.next的值一样，那么head.next= head.next.next,否则后移
	//     方式二：指针转化
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(1);
		ListNode node3 = new ListNode(2);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		// ListNode res = deleteDuplicates1(node1);
		// while (res != null) {
		// 	System.out.print(res.val + " ");
		// 	res = res.next;
		// }

		ListNode res1 = deleteDuplicates2(node1);
		while (res1 != null) {
			System.out.print(res1.val + " ");
			res1 = res1.next;
		}
	}

	//方式一：标记链表的头结点，从head开始，如果head的值跟head.next的值一样，那么head.next= head.next.next,否则后移
	public static ListNode deleteDuplicates1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode res = head;
		while (head.next != null) {
			if (head.val == head.next.val) {
				head.next = head.next.next;
			} else {
				head = head.next;
			}
		}
		return res;
	}

	//方式二：创建一个hashset，里面放置listnode
	public static ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		HashSet<Integer> set = new HashSet<>();
		set.add(head.val);
		ListNode res = head;
		while (head.next != null) {
			if (set.contains(head.next.val)) {
				head.next = head.next.next;
			} else {
				set.add(head.next.val);
				head = head.next;
			}
			// if(set.add(head.next.val)){
			// 	head = head.next;
			// }else{
			// 	head.next = head.next.next;
			// }
		}
		head.next = null;
		return res;
	}
}
