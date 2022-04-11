package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/4 12:48 上午
 */
public class ReverseListFromMToN {
	//NC21 链表内指定区间反转
	//样例：1,2,3,4,5   2  4 返回 1 4 3 2 5
	//分析：给你一个链表，进行翻转后返回,进行区间翻转
	//思路：在m -n的区间内翻转，定义两个节点，亚节点，头结点，先遍历到m 然后进行翻转操作，然后到n 接上
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
		ListNode res1 = reverseBetween(node1, 2, 4);
		while (res1 != null) {
			System.out.print(res1.val + " ");
			res1 = res1.next;
		}
	}

	public static ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = head;
		for(int i = 0;i<m-1;i++){
			pre = pre.next;
			cur = cur.next;
		}
		for(int i = 0;i<n-m;i++){
			ListNode temp = cur.next;
			cur.next = temp.next;
			temp.next = pre.next;
			pre.next = temp;
		}
		return dummy.next;
	}
}
