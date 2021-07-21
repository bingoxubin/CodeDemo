package com.bingoabin.algorithm.listnode;

/**
 * @author xubin34
 * @date 2021/7/22 12:45 上午
 */
public class CopyRandomListNode {
	//LeetCode 138. 复制带随机指针的链表
	//样例：输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
	//     输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
	//分析：给一个链表 其中每个节点都有一个随机指针指向任意一个，进行随机链表的复制
	//思路：
	public static void main(String[] args) {
		Node node1 = new Node(7);
		Node node2 = new Node(13);
		Node node3 = new Node(11);
		Node node4 = new Node(10);
		Node node5 = new Node(1);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = null;
		node1.random = null;
		node2.random = node1;
		node3.random = node5;
		node4.random = node3;
		node5.random = node1;
		System.out.println(copyRandomList(node1).val);
	}

	//思路：在原链表中间，每个节点后先插入一个节点
	//     然后，从头开始，复制random节点，判断node.random是否为空，如果不为空，那么node.next.random = node.random.next 否则：node.next.random = null
	//     最后进行拆分
	public static Node copyRandomList(Node head) {
		if(head == null) return head;
		Node node = head;
		while(node != null){
			Node temp = new Node(node.val);
			temp.next = node.next;
			node.next = temp;
			node = node.next.next;
		}
		node = head;
		while(node != null){
			if(node.random != null){
				node.next.random = node.random.next;
			}else{
				node.next.random = null;
			}
			node = node.next.next;
		}
		node = head;
		Node res = head.next;
		Node p = null;
		while(node != null){
			p = node.next;
			node.next = node.next.next;
			if(p.next == null){
				p.next = null;
			}else{
				p.next = p.next.next;
			}
			node = node.next;
		}
		return res;
	}

	//自己实现一遍
	public static Node copyRandomList1(Node head){
		if(head == null) return head;
		Node node = head;
		while(node != null){
			Node temp = new Node(node.val);
			temp.next = node.next;
			node.next = temp;
			node = node.next.next;
		}
		node = head;
		while(node != null){
			if(node.random != null){
				node.next.random = node.random.next;
			}else{
				node.next.random = null;
			}
			node = node.next.next;
		}
		node = head;
		Node res = node.next;
		Node pre = null;
		while(node != null){
			pre = node.next;
			node.next = node.next.next;
			if(pre.next == null){
				pre.next = null;
			}else{
				pre.next = pre.next.next;
			}
			node = node.next;
		}
		return res;
	}
}

class Node {
	int val;
	Node next;
	Node random;
	Node(int val) {
		this.val = val;
	}
}
