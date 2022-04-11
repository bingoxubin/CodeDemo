package com.bingoabin.algorithm.listnode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author xubin03
 * @date 2021/6/4 11:03 上午
 */
public class MergeKthList {
	//NC51 合并k个已排序的链表
	//样例：一条链表：1 6 9 12 15  另一条链表：2，3，10，13，18  返回 1，2，3，6，9，10.12.13.15.18
	//分析：将k个有序链表进行合并
	//思路：方式一：迭代，一个个合并
	//     方式二：采用优先队列
	//     方式三：两两合并  迭代
	//     方式四：两辆合并  递归
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(6);
		ListNode node3 = new ListNode(9);
		ListNode node4 = new ListNode(12);
		ListNode node5 = new ListNode(15);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		ListNode node11 = new ListNode(2);
		ListNode node22 = new ListNode(3);
		ListNode node33 = new ListNode(10);
		ListNode node44 = new ListNode(13);
		ListNode node55 = new ListNode(18);
		node11.next = node22;
		node22.next = node33;
		node33.next = node44;
		node44.next = node55;

		ArrayList<ListNode> lists = new ArrayList<>();
		lists.add(node1);
		lists.add(node11);

		ListNode res = mergeKLists4(lists);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}

	//方式一：迭代 定义一个哑节点做头部，后面每次在list里面找一个节点放到亚节点后面
	public static ListNode mergeKLists1(ArrayList<ListNode> lists) {
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (true) {
			ListNode temp = null;
			int flag = -1;
			for (int i = 0; i < lists.size(); i++) {
				if (lists.get(i) == null) {
					continue;
				}
				if (temp == null || lists.get(i).val < temp.val) {
					temp = lists.get(i);
					flag = i;
				}
			}
			if (flag == -1) {
				break;
			}
			res.next = temp;
			res = res.next;
			lists.set(flag, lists.get(flag).next);
		}
		return dummy.next;
	}

	//方式二：优先队列,创建一个队列，然后将lists中的头结点放入到queue中，然后遍历queue，不断弹出，弹出一个并放入一个弹出的next，直到queue中没有数据
	public static ListNode mergeKLists2(ArrayList<ListNode> lists) {
		PriorityQueue<ListNode> queue = new PriorityQueue<>(((o1, o2) -> o1.val - o2.val));
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				queue.offer(lists.get(i));
			}
		}
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (!queue.isEmpty()) {
			ListNode temp = queue.poll();
			res.next = temp;
			res = res.next;
			if (temp.next != null) {
				queue.offer(temp.next);
			}
		}
		return dummy.next;
	}

	//方式三：两两合并，迭代,将第一个，第二个合并到lists的第一个，将第三个，第四个合并到lists的第二个，不断循环，知道lists中只有一个
	public static ListNode mergeKLists3(ArrayList<ListNode> lists) {
		if (lists.size() == 0) {
			return null;
		}
		int len = lists.size();
		while (len > 1) {
			int temp = 0;
			for (int i = 0; i < len; i = i + 2) {
				if (i == len - 1) {
					lists.set(temp++, lists.get(i));
				} else {
					lists.set(temp++, mergeIter(lists.get(i), lists.get(i + 1)));
				}
			}
			len = temp;
		}
		return lists.get(0);
	}

	private static ListNode mergeIter(ListNode node1, ListNode node2) {
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (node1 != null && node2 != null) {
			if (node1.val < node2.val) {
				res.next = node1;
				node1 = node1.next;
			} else {
				res.next = node2;
				node2 = node2.next;
			}
			res = res.next;
		}
		res.next = node1 == null ? node2 : node1;
		return dummy.next;
	}

	//方式四：两辆合并，递归
	public static ListNode mergeKLists4(ArrayList<ListNode> lists) {
		if (lists.size() == 0) {
			return null;
		}
		return merge(lists, 0, lists.size() - 1);
	}

	public static ListNode merge(ArrayList<ListNode> lists, int left, int right) {
		if (left == right) {
			return lists.get(left);
		}
		int mid = (right - left) / 2 + left;
		ListNode node1 = merge(lists, left, mid);
		ListNode node2 = merge(lists, mid + 1, right);
		return mergeRecur(node1, node2);
	}

	public static ListNode mergeRecur(ListNode node1, ListNode node2) {
		if (node1 == null) {
			return node2;
		}
		if (node2 == null) {
			return node1;
		}
		if (node1.val < node2.val) {
			node1.next = mergeRecur(node1.next, node2);
			return node1;
		}
		node2.next = mergeRecur(node1, node2.next);
		return node2;
	}
}
