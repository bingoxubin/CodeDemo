package com.bingoabin.algorithm.listnode;

/**
 * @author xubin03
 * @date 2021/6/1 12:09 上午
 */
public class DeleteLastKth {
	//NC53 删除链表倒数第N个节点  https://www.nowcoder.com/practice/f95dcdafbde44b22a6d741baf71653f6?tpId=117&&tqId=37750&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：1→2→3→4→5, n= 2 返回 1 2 3 5
	//分析：给定一个链表，删除链表的倒数第 nn 个节点并返回链表的头指针
	//思路：定义一个哑节点，在head之前，先让head走n步，然后亚节点开始走，当走到head没有了，删掉亚节点后面一个节点
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

		ListNode res = removeNthFromEnd(node1, 2);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		//创建哑节点
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		//定义两个节点，second先走，first后走
		ListNode first = dummy;
		ListNode second = head;
		//second先走n
		for (int i = 0; i < n; i++) {
			second = second.next;
		}
		//然后一起走
		while (second != null) {
			first = first.next;
			second = second.next;
		}
		//删掉first的后面一个
		first.next = first.next.next;
		return dummy.next;
	}
}
