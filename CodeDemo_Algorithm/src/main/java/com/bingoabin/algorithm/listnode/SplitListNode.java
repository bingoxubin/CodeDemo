package com.bingoabin.algorithm.listnode;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/9/22 8:39 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SplitListNode {
	//Leetcode 725. 分隔链表
	//示例：输入: root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
	//     输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
	//     解释: 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
	//分析：给一个链表，将一个链表等分成k个部分，如果有余数，左边的长度大于右边的长度，因此有的部分为null，长度差不能超过1
	//思路：先统计链表的长度，然后计算每个位置的基础长度std，以及多余的部分余数more，more表示前面有多少个位置是多1个长度的
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;

		SplitListNode splitListNode = new SplitListNode();
		System.out.println(Arrays.toString(splitListNode.splitListToParts(node1, 4)));
	}

	public ListNode[] splitListToParts(ListNode head, int k) {
		//计算链表长度
		int len = getLen(head);
		//计算数组中每个位置的基础长度
		int std = len / k;
		//多出来的长度
		int more = len % k;
		//定义结果值
		ListNode[] res = new ListNode[k];
		for (int i = 0; i < len && head != null; i++) {
			//该位置为 head；
			res[i] = head;
			//那么这个位置的长度为size
			int size = std + (i < more ? 1 : 0);
			for (int j = 1; j < size; j++) {
				head = head.next;
			}
			//缓存起来 下一个头节点
			ListNode next = head.next;
			//该位置最后一个后面是null结尾
			head.next = null;
			//head指向下一个头结点
			head = next;
		}
		return res;
	}

	//统计链表的长度
	public int getLen(ListNode head) {
		int count = 0;
		if (head == null) return count;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}
}
