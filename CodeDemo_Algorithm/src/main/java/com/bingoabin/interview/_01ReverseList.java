package com.bingoabin.interview;

/**
 * @author bingoabin
 * @date 2023/5/22 18:23
 * @Description:
 */
public class _01ReverseList {
	//方式一：迭代
	public ListNode reverseList1(ListNode head) {
		ListNode pre = null;
		while(head != null){
			ListNode temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		return pre;
	}

	//方式二：递归
	public ListNode reverseList2(ListNode head){
		if(head == null || head.next == null) return head;
		ListNode res = reverseList2(head.next);
		head.next.next = head;
		head.next = null;
		return res;
	}

	//方式三：头插
	public ListNode reverseList(ListNode head){
		ListNode dummy = new ListNode(-1);
		while(head != null){
			ListNode temp = head.next;
			head.next = dummy.next;
			dummy.next = head;
			head = temp;
		}
		return dummy.next;
	}
}

class ListNode{
	int val;
	ListNode next;
	ListNode(){}
	ListNode(int val){
		this.val = val;
	}
}
