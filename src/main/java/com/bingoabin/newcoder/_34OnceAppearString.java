//第一个只出现一次的字符位置
//在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.

//哈希思想：
// 1.计算各个字符出现的次数。遍历所有字符，在下标为x的数组元素加1，其中x为正在遍历的字符对应的ASCII码。
// 2.遍历所有字符，并一边判断上一步得到的数组的元素是否为1.为1则返回该字符在String中的位置。
package com.bingoabin.newcoder;

import java.util.HashMap;

public class _34OnceAppearString {
	public class Solution {
		public int FirstNotRepeatingChar(String str) {
			HashMap<Character, Integer> map = new HashMap<Character, Integer>();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (map.containsKey(c)) {
					int time = map.get(c);
					time++;
					map.put(c, time);
				} else {
					map.put(c, 1);
				}
			}
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (map.get(c) == 1)
					return i;
			}
			return -1;
		}
	}

	public class Solution1 {
		public int FirstNotRepeatingChar(String str) {
			if (str == null) {
				return -1;
			}
			int[] hashCount = new int[256];
			for (int i = 0; i < str.length(); i++) {
				hashCount[str.charAt(i)]++;
			}

			for (int i = 0; i < str.length(); i++) {
				if (hashCount[str.charAt(i)] == 1) {
					return i;
				}
			}
			return -1;
		}
	}
}
