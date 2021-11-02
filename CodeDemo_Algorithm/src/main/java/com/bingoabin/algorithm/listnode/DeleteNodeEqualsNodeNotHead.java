package com.bingoabin.algorithm.listnode;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/2 10:09 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DeleteNodeEqualsNodeNotHead {
	//Leetcode 237. 删除链表中的节点
	//示例：输入：head = [4,5,1,9], node = 5
	//     输出：[4,1,9]
	//     解释：指定链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
	//分析：给一个链表，进行删除其中指定的节点，但是不知道头节点，因此无法进行遍历
	//思路：进行原地删除，指针指向后一个即可
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

		DeleteNodeEqualsNodeNotHead deleteNodeEqualsNodeNotHead = new DeleteNodeEqualsNodeNotHead();
		deleteNodeEqualsNodeNotHead.deleteNode(node2);
		while (node1 != null) {
			out.print(node1.val + " ");
			node1 = node1.next;
		}
	}

	//先将要删除的节点的 后一个值 拷贝给当前node
	//然后删除下一个节点，即当前指针指向下下个节点，即可
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
}
