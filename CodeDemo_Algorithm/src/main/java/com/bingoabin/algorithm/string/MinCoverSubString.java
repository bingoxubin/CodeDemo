package com.bingoabin.algorithm.string;

import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/6/9 10:11 上午
 */
public class MinCoverSubString {
	//NC28 最小覆盖子串
	//样例：输入"XDOYEZODEYXNZ","XYZ"输出：XYNZ
	//分析：给出两个字符串 SS 和 TT，要求在O(n)的时间复杂度内在 SS 中找出最短的包含 TT 中所有字符的子串。
	//思路：定义一个need的hashmap，以及window的hashmap，然后将need的字符都加到need的hashmap中，计数
	//     while循环判断当没达到右边界的时候，如果check()【window包含need中的所有数并且个数大于need的个数，然后left开始右移】，否则right右移
	public static void main(String[] args) {
		MinCoverSubString minCoverSubString = new MinCoverSubString();
		String str1 = "XDOYEZODEYXNZ";
		String str2 = "XYZ";
		System.out.println(minCoverSubString.minWindow(str1, str2));
	}

	HashMap<Character, Integer> need = new HashMap<>();
	HashMap<Character, Integer> window = new HashMap<>();

	public String minWindow(String s, String t) {
		int left = 0;
		int right = -1;
		//构建need的hashmap
		for (int i = 0; i < t.length(); i++) {
			need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
		}
		int reslen = Integer.MAX_VALUE, resleft = 0, resright = 0;
		while (right < s.length()) {
			//不满足check条件的时候  只管window里面放
			right++;
			if (right < s.length() && need.containsKey(s.charAt(right))) {
				window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
			}
			//满足条件了
			while (check() && left <= right) {
				//记录最大值结果
				if (right - left + 1 < reslen) {
					reslen = right - left + 1;
					resleft = left;
					resright = right + 1;
				}
				//如果去掉了left在need里面，需要window里面也剪掉
				if (need.containsKey(s.charAt(left))) {
					window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
				}
				left++;
			}
		}

		return reslen == Integer.MAX_VALUE ? "" : s.substring(resleft, resright);
	}

	//判断window 是否符合 need
	public boolean check() {
		for (Character character : need.keySet()) {
			if (window.getOrDefault(character, 0) < need.get(character)) {
				return false;
			}
		}
		return true;
	}
}
