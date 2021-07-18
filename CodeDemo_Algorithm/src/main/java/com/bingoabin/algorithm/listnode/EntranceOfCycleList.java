package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/5/31 11:03 下午
 */
public class EntranceOfCycleList {
	//NC3 链表中环的入口节点  https://www.nowcoder.com/practice/6e630519bf86480296d0f1c868d425ad?tpId=117&&tqId=37713&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：1,2,3,4,5,3 返回 3
	//分析：对于一个给定的链表，返回环的入口节点，如果没有环，返回null
	//思路：采用快慢指针的方式，如果快指针能追到满指针，那么表示有环，追到的时候快指针回到开始节点，继续一个一个走
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
		node5.next = node3;

		System.out.println(detectCycle(node1).val);
	}

	public static ListNode detectCycle(ListNode head) {
		if (head == null) return head;
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				fast = head;
				while (slow != fast) {
					fast = fast.next;
					slow = slow.next;
				}
				break;
			}
		}
		if (fast == null || fast.next == null) {
			return null;
		}
		return fast;
	}
}
