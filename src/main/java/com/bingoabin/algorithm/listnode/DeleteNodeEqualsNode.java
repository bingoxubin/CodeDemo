package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/3 12:44 下午
 */
public class DeleteNodeEqualsNode {
	//Leetcode 203. 移除链表元素
	//样例：输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]
	//分析：给一个链表 删除其中为指定元素的值
	//思路：一个节点pre指向亚节点，一个cur指向head，当cur.val = val,那么pre.next = cur.next;如果不相等，那么pre = cur,意思就是向后移，最后判断完就cur = cur.next;
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

		ListNode res = removeElements(node1, 1);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}

	public static ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			if (cur.val == val) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return dummy.next;
	}
}
