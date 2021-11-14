package com.bingoabin.algorithm.design;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/14 1:14 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MapSum {
	//Leetcode 677. 键值映射
	//示例：输入：
	//     ["MapSum", "insert", "sum", "insert", "sum"]
	//     [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
	//     输出：
	//     [null, null, 3, null, 5]
	//分析：实现一个 MapSum 类，支持两个方法，insert和sum：
	//     MapSum() 初始化 MapSum 对象
	//     void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
	//     int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
	//思路：采用hashmap来计数
	HashMap<String, Integer> map;

	public MapSum() {
		map = new HashMap<>();
	}

	public void insert(String key, int val) {
		map.put(key, val);
	}

	public int sum(String prefix) {
		int count = 0;
		for (Map.Entry<String, Integer> value : map.entrySet()) {
			if (value.getKey().startsWith(prefix)) {
				count += value.getValue();
			}
		}
		return count;
	}

	//测试
	public static void main(String[] args) {
		MapSum mapSum = new MapSum();
		mapSum.insert("apple", 3);
		out.println(mapSum.sum("ap"));
		mapSum.insert("app", 2);
		out.println(mapSum.sum("ap"));
	}
}
