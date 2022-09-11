package com.bingoabin.algorithm.listnode;

/**
 * @author bingoabin
 * @date 2022/9/8 22:57
 * @Description: 双向链表  有问题  还未实现
 */
public class DoubleLinked {
	private DNode head;
	private DNode tail;

	public DoubleLinked() {
		head = new DNode(-1);
		tail = new DNode(-1);
		head.after = tail;
		tail.pre = head;
	}

	//索引下标插入一个节点
	public DNode insertNode(int index, DNode node) {
		if(index < 0){
			throw new RuntimeException("添加的索引节点下标必须从0开始");
		}
		DNode cur = head;
		for (int i = 0; i <= index; i++) {
			cur = cur.after;
			if(cur == null){
				throw new RuntimeException("添加的索引下标超过了链表的长度");
			}
		}
		DNode temp = cur.pre;
		node.after = cur;
		node.pre = temp;
		temp.after = node;
		cur.pre = node;

		//删掉最后一个节点
		tail.pre.after = null;
		tail.pre = null;
		return head.after;
	}

	//测试
	public static void main(String[] args){
		DoubleLinked doubleLinked = new DoubleLinked();
		doubleLinked.insertNode(0, new DNode(1));
		DNode node2 = doubleLinked.insertNode(1, new DNode(2));
		while(node2 != null){
			System.out.println(node2.val);
			node2 = node2.after;
		}
	}
}

class DNode {
	int val;
	DNode pre;
	DNode after;

	DNode(int val) {
		this.val = val;
	}
}
