package com.bingoabin.technology.hashmap;

/**
 * @author bingoabin
 * @date 2021/6/14 1:13
 */
public class MyHashMap {
	public static void main(String[] args) {
		String str = "nihao";
		int h;
		System.out.println((str == null) ? 0 : ((h = str.hashCode()) ^ (h >>> 16)) & 15);
		MyHashMap myHashMap = new MyHashMap();
		myHashMap.put(1, 1);
		System.out.println("【插入(1,1)获取1】获取第一次插入:" + myHashMap.get(1));
		myHashMap.put(1, 3);
		System.out.println("【插入(1,3)获取1】验证key一样的时候，进行覆盖：" + myHashMap.get(1));
		myHashMap.put(17, 2);
		System.out.println("【插入(17,2)获取17】代码中长度为16的数组，17和1为hash冲突，验证冲突也能保存：" + myHashMap.get(17));
		System.out.println("【再次获取1】验证冲突后，原值不修改：" + myHashMap.get(1));
	}

	Node[] map;
	int len = 10000;

	public MyHashMap() {
		map = new Node[len];
	}

	public void put(int key, int value) {
		int index = key % len;
		if (map[index] == null) {
			map[index] = new Node(-1, -1);
			map[index].down = new Node(key, value);
		} else {
			Node node = map[index];
			while (node.down != null) {
				if (node.down.key == key) {
					node.down.value = value;
					return;
				}
				node = node.down;
			}
			node.down = new Node(key, value);
		}
	}

	public int get(int key) {
		int index = key % len;
		if (map[index] != null) {
			Node node = map[index];
			while (node != null) {
				if (node.key == key) {
					return node.value;
				}
				node = node.down;
			}
		}
		return -1;
	}

	public void remove(int key) {
		int index = key % len;
		if (map[index] != null) {
			Node node = map[index];
			while (node != null && node.down != null) {
				if (node.down.key == key) {
					node.down = node.down.down;
				}
				node = node.down;
			}
		}
	}

	class Node {
		int key;
		int value;
		Node down;

		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}
