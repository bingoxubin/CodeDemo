package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/3 12:44 下午
 */
public class DeleteRepetitionNodeAll {
	//NC24 删除有序链表中重复的元素(都删除）
	//样例：给出的链表为1 1 2 3 3 返回 2
	//分析：删除给出链表中的重复元素（链表中元素从小到大有序），使链表中相同的都删除
	//思路：创建一个亚节点cur，从head开始遍历，如果head.val == head.next.val那么接一个while循环，如果还相等，head= head.next;如果不相等了，那么head后移以为，然后cur.next = head;
	//                                  如果不相等，那么head后移，cur后移即可
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

		ListNode res1 = deleteDuplicates(node1);
		while (res1 != null) {
			System.out.print(res1.val + " ");
			res1 = res1.next;
		}
	}

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode cur = dummy;
		while (head != null) {
			if (head.next != null && head.val == head.next.val) {
				while (head.next != null && head.val == head.next.val) {
					head = head.next;
				}
				head = head.next;
				cur.next = head;
			} else {
				head = head.next;
				cur = cur.next;
			}
		}
		return dummy.next;
	}
}
