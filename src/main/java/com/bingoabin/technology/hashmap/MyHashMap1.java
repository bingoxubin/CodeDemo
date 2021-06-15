package com.bingoabin.technology.hashmap;

/**
 * @author bingo
 * @date 2020/12/3 12:21
 */
public class MyHashMap1 {
	public static void main(String[] args) {
		String str = "nihao";
		int h;
		System.out.println((str == null) ? 0 : ((h = str.hashCode()) ^ (h >>> 16)) & 15);
		MyHashMap1 myHashMap = new MyHashMap1();
		myHashMap.put(1, 1);
		System.out.println("【插入(1,1)获取1】获取第一次插入:" + myHashMap.get(1));
		myHashMap.put(1, 3);
		System.out.println("【插入(1,3)获取1】验证key一样的时候，进行覆盖：" + myHashMap.get(1));
		myHashMap.put(17, 2);
		System.out.println("【插入(17,2)获取17】代码中长度为16的数组，17和1为hash冲突，验证冲突也能保存：" + myHashMap.get(17));
		System.out.println("【再次获取1】验证冲突后，原值不修改：" + myHashMap.get(1));
	}

	Node[] arr;

	public MyHashMap1() {
		arr = new Node[16];
	}

	public void put(int key, int value) {
		int index = key % 16;
		if (arr[index] == null) {
			arr[index] = new Node(key, value);
		} else {
			Node first = arr[index];
			if (first.key == key) first.value = value;
			//判断hashmap中是否有这个值
			boolean flag = true;
			while (first.down != null) {
				if (first.key == key) {
					first.value = value;
					flag = false;
				}
				first = first.down;
			}
			if (flag) {
				first.down = new Node(key, value);
			}
		}
	}

	public int get(int key) {
		int index = key % 16;
		if (arr[index] == null) return -1;
		Node node = arr[index];
		while (node != null) {
			if (node.key == key) {
				return node.value;
			}
			node = node.down;
		}
		return -1;
	}

	class Node {
		int key;
		int value;
		Node down;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}


