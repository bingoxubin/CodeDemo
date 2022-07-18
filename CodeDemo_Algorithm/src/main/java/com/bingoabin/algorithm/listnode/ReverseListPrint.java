package com.bingoabin.algorithm.listnode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/7/18 9:56
 */
public class ReverseListPrint {
	//Leetcode 剑指 Offer 06. 从尾到头打印链表
	//示例：示例 1：
	//      输入：head = [1,3,2]
	// 	    输出：[2,3,1]
	//分析：输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
	//思路：

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(2);
		node1.next = node2;
		node2.next = node3;

		ReverseListPrint reverseListPrint = new ReverseListPrint();
		System.out.println(Arrays.toString(reverseListPrint.reversePrint(node1)));
		System.out.println(Arrays.toString(reverseListPrint.reversePrint1(node1)));
	}

	public int[] reversePrint(ListNode head) {
		List<Integer> res = new ArrayList<>();
		while (head != null) {
			res.add(head.val);
			head = head.next;
		}
		int i = 0;
		int j = res.size() - 1;
		while (i < j) {
			int temp = res.get(i);
			res.set(i, res.get(j));
			res.set(j, temp);
			i++;
			j--;
		}
		return res.stream().mapToInt(Integer::new).toArray();
	}

	public int[] reversePrint1(ListNode head) {
		List<Integer> res = new ArrayList<>();
		while (head != null) {
			res.add(0, head.val);
			head = head.next;
		}
		return res.stream().mapToInt(Integer::new).toArray();
	}
}
