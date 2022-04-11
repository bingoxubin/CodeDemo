package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author bingoabin
 * @date 2021/7/10 1:56
 */
public class RealizeTimeMap {
	//LeetCode 981. 基于时间的键值存储
	//案例：输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
	// 输出：[null,null,"bar","bar",null,"bar2","bar2"]
	// 解释： 
	// TimeMap kv;  
	// kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1  
	// kv.get("foo", 1);  // 输出 "bar"  
	// kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）  
	// kv.set("foo", "bar2", 4);  
	// kv.get("foo", 4); // 输出 "bar2"  
	// kv.get("foo", 5); // 输出 "bar2"  

	//分析：创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
	// 1. set(string key, string value, int timestamp)
	// 存储键 key、值 value，以及给定的时间戳 timestamp。
	// 2. get(string key, int timestamp)
	// 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
	// 如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
	// 如果没有值，则返回空字符串（""）。

	//思路：通过hashmap实现
	public static void main(String[] args) {
		TimeMap timeMap = new TimeMap();
		timeMap.set("foo", "bar", 1);
		System.out.println(timeMap.get("foo", 1));
		System.out.println(timeMap.get("foo", 3));
		timeMap.set("foo", "bar2", 4);
		System.out.println(timeMap.get("foo", 4));
		System.out.println(timeMap.get("foo", 5));
	}
}

//定义TimeMap 实现构造方法 set 以及get方法
class TimeMap {
	HashMap<String, TreeMap<Integer, String>> map;

	public TimeMap() {
		map = new HashMap<>();
	}

	public void set(String key, String value, int timestamp) {
		// 检查一下有没有哈希表，如果有的话就直接添加，没有的话就需要 new 出来一个再添加 key-value
		// 嵌套哈希表需要定义比较函数 Comparator ，然后按照从大到小排序
		TreeMap<Integer, String> mapper = map.getOrDefault(key, new TreeMap<Integer, String>((o1, o2) -> o2 - o1));
		mapper.put(timestamp, value);
		map.put(key, mapper);
	}

	public String get(String key, int timestamp) {
		TreeMap<Integer, String> mapper = map.get(key);
		if (mapper == null || mapper.isEmpty()) {
			return "";
		} else {
			for (Integer val : mapper.keySet()) {
				if (val <= timestamp) {
					return mapper.get(val);
				}
			}
		}
		return "";
	}
}
