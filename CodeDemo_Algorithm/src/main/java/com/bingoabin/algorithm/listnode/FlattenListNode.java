package com.bingoabin.algorithm.listnode;

/**
 * @Author: xubin34
 * @Date: 2021/9/24 9:50 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FlattenListNode {
	//LeetCode 430. 扁平化多级双向链表
	//示例：注意：有child先走child 然后再走发出child的next 不断继续
	//分析：多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
	//     给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
	//思路：方式一：递归
	//     方式二：迭代
	public static void main(String[] args) {
		PNode pNode1 = new PNode(1);
		PNode pNode2 = new PNode(2);
		PNode pNode3 = new PNode(3);
		PNode pNode4 = new PNode(4);
		PNode pNode5 = new PNode(5);
		PNode pNode6 = new PNode(6);
		PNode pNode7 = new PNode(7);
		PNode pNode8 = new PNode(8);
		PNode pNode9 = new PNode(9);
		PNode pNode10 = new PNode(10);
		PNode pNode11 = new PNode(11);
		PNode pNode12 = new PNode(12);

		pNode1.next = pNode2;
		pNode2.prev = pNode1;
		pNode2.next = pNode3;
		pNode3.prev = pNode2;
		pNode3.child = pNode7;
		pNode7.next = pNode8;
		pNode8.prev = pNode7;
		pNode8.child = pNode11;
		pNode11.next = pNode12;
		pNode12.prev = pNode11;
		pNode8.next = pNode9;
		pNode9.prev = pNode8;
		pNode9.next = pNode10;
		pNode10.prev = pNode9;
		pNode3.next = pNode4;
		pNode4.prev = pNode3;
		pNode4.next = pNode5;
		pNode5.prev = pNode4;
		pNode5.next = pNode6;
		pNode6.prev = pNode5;
		FlattenListNode flattenListNode = new FlattenListNode();
		System.out.println(flattenListNode.flatten1(pNode1).val);
		System.out.println(flattenListNode.flatten2(pNode1).val);
	}

	//递归 有child先走child 然后再走发出child的next 不断继续
	public PNode flatten1(PNode head) {
		PNode dummy = new PNode(0);
		dummy.next = head;
		while (head != null) {
			if (head.child == null) {
				head = head.next;
			} else {
				PNode tmp = head.next;
				PNode chead = flatten1(head.child);
				head.next = chead;
				chead.prev = head;
				head.child = null;
				while (head.next != null) head = head.next;
				head.next = tmp;
				if (tmp != null) tmp.prev = head;
				head = tmp;
			}
		}
		return dummy.next;
	}

	//迭代  有child先走child 然后再走发出child的next 不断继续
	//1---2---3---4---5---6--NULL
	//        |
	//        7---8---9---10--NULL
	//            |
	//           11--12--NULL
	public PNode flatten2(PNode head) {
		PNode dummy = new PNode(0);
		dummy.next = head;
		while (head != null) {
			if (head.child == null) {
				head = head.next;
			} else {
				PNode tmp = head.next;
				PNode child = head.child;
				head.next = child;
				child.prev = head;
				head.child = null;
				PNode last = head;
				while (last.next != null) last = last.next;
				last.next = tmp;
				if (tmp != null) tmp.prev = last;
				head = head.next;
			}
		}
		return dummy.next;
	}

	//自己实现一遍
	public PNode flatten3(PNode head) {
		PNode dummy = new PNode(0);
		dummy.next = head;
		while (head != null) {
			if (head.child == null) {
				head = head.next;
			} else {
				PNode temp = head.next;
				PNode child = head.child;
				head.next = child;
				child.prev = head;
				head.child = null;

				PNode last = head;
				while (last != null) last = last.next;
				last.next = temp;
				if (temp != null) temp.prev = last;
				head = head.next;
			}
		}
		return dummy.next;
	}
}

class PNode {
	int val;
	PNode prev;
	PNode next;
	PNode child;

	public PNode(int val) {
		this.val = val;
	}
}
