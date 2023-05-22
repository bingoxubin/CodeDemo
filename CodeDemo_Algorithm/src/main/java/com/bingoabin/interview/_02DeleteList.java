package com.bingoabin.interview;

/**
 * @author bingoabin
 * @date 2023/5/22 18:48
 * @Description:
 */
public class _02DeleteList {
	//只要重复全部删除
	public ListNode deleteDuplicates(ListNode head){
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode res = dummy;
		while(head != null){
			if(head.next != null && head.val == head.next.val){
				while(head.next != null && head.val == head.next.val){
					head = head.next;
				}
				head = head.next;
				res.next = head;
			}else{
				head = head.next;
				res = res.next;
			}
		}
		return dummy.next;
	}

	//重复的保留一个
	public ListNode deleteDuplicatesOne(ListNode head){
		ListNode res = head;
		if(res == null) return res;
		while(head.next != null){
			if(head.val == head.next.val){
				head.next = head.next.next;
			}else{
				head = head.next;
			}
		}
		return res;
	}

	//刪除指定的鏈錶元素
	public ListNode removeElements(ListNode head,int val){
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = head;
		while(cur != null){
			if(cur.val == val){
				pre.next = cur.next;
			}else{
				pre = cur;
			}
			cur = cur.next;
		}
		return dummy.next;
	}
}
