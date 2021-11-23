package com.bingoabin.algorithm.string;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/23 10:14 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class BuddyString {
	//Leetcode 859. 亲密字符串
	//示例：示例 1：
	//     输入：s = "ab", goal = "ba"
	//     输出：true
	//     解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
	//     示例 2：
	//     输入：s = "ab", goal = "ab"
	//     输出：false
	//     解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
	//分析：给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回true；否则返回 false 。
	//     交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
	//     例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
	//思路：
	public static void main(String[] args) {
		//ab ab   ->false
		//aab aab ->true
		//ab ba   ->true
		//abc cab ->false
		String s = "abc";
		String goal = "cab";
		BuddyString buddyString = new BuddyString();
		out.println(buddyString.buddyStrings1(s, goal));
		out.println(buddyString.buddyStrings2(s, goal));
	}

	//枚举方式
	public boolean buddyStrings1(String A, String B) {
		//如果两个字符串长度不一样，直接false
		if (A.length() != B.length()) return false;
		//如果两个字符串一模一样
		if (A.equals(B)) {
			for (int i = 0; i < A.length() - 1; i++) {
				//判断A 如果A中有重复的就返回true;也就是A中从最开始的字符开始找，如果后面有相同字符就返回true;
				if (A.indexOf(A.charAt(i), i + 1) != -1) return true;
			}
		}
		//如果是亲密字符串，最多只可能两个顺序不一致，其他都一致
		//因此标记两个不一样字符的位置，并且记下不一样的字符个数，超过2个就是false
		int index1 = -1;
		int count = 0;
		int index2 = -1;
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) != B.charAt(i)) {
				count++;
				if (count == 1) {
					index1 = i;
				} else if (count == 2) {
					index2 = i;
				} else {
					return false;
				}
			}
		}
		return count == 2 && A.charAt(index1) == B.charAt(index2) && B.charAt(index1) == A.charAt(index2);
	}

	//实现一遍
	public boolean buddyStrings2(String A, String B) {
		if (A.length() != B.length()) return false;
		if (A.equals(B)) {
			for (int i = 0; i < A.length(); i++) {
				if (A.indexOf(A.charAt(i), i + 1) != -1) return true;
			}
		}
		int index1 = -1;
		int index2 = -1;
		int count = 0;
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) != B.charAt(i)) {
				count++;
				if(count == 1){
					index1 = i;
				}else if(count == 2){
					index2 = i;
				}else{
					return false;
				}
			}
		}
		return count == 2 && A.charAt(index1) == B.charAt(index2) && A.charAt(index2) == B.charAt(index1);
	}
}
