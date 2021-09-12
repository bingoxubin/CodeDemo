package com.bingoabin.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xubin34
 * @Date: 2021/9/12 3:20 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CheckValidString {
	//Leetcode 678. 有效的括号字符串
	//示例：输入: "(*))"  输出: True
	//分析：检测是不是有效的括号字符串，*可以表示左括号 或者有括号 或者为空 什么都不表示
	//思路：方式一：栈的方式：
	//                    定义一个左括号栈，定义一个*号栈，
	//                    如果碰到左括号放到左括号栈，碰到*号放到*括号栈，
	//                    如果是右括号那么，判断左边括号栈是否为空，不为空弹出一个，如果为空，判断*号栈是否为空，不为空弹出，如果也为空，那么return fasle；
	//                    最后，遍历左边括号栈 以及 *号栈  一个一个弹出,如果左边括号栈弹出的下标值 > *号栈弹出的下标值  return false
	//                    最后返回判断  左边括号栈是否为空即可

	//     方式二：贪心算法：
	//                    定义未匹配的左括号数量的最小值和最大值 两个int值
	//                    如果遇到左括号 那么最小值 跟最大值都 + 1；
	//                    如果遇到右括号  那么最小值 跟最大值都 - 1；
	//                    如果遇到* 号  那么将最小值 -1  最大值+1；
	//                    未匹配的左括号数量，必须非负，如果最大值小于0的时候，说明没有左括号可以与右括号进行匹配，因此返回false
	//                    当最小值小于0的时候，不应该继续减 应该保持0
	//                    遍历结束时，所有的左括号都应和右括号匹配，因此只有当最小值为 00 时，字符串 ss 才是有效的括号字符串。
	public static void main(String[] args) {
		String str = "(*))";
		CheckValidString checkValidString = new CheckValidString();
		System.out.println(checkValidString.checkValidString1(str));
		System.out.println(checkValidString.checkValidString2(str));
	}

	//方式一：栈的方式
	public boolean checkValidString1(String s) {
		//定义一个左括号栈，定义一个*号栈
		Deque<Integer> left = new LinkedList<>();
		Deque<Integer> xing = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			//如果碰到左括号放到左括号栈，碰到*号放到*括号栈
			if (s.charAt(i) == '(') {
				left.push(i);
			} else if (s.charAt(i) == '*') {
				xing.push(i);
			} else { //如果是右括号那么，判断左边括号栈是否为空，不为空弹出一个，如果为空，判断*号栈是否为空，不为空弹出，如果也为空，那么return fasle；
				if (!left.isEmpty()) {
					left.pop();
				} else if (!xing.isEmpty()) {
					xing.pop();
				} else {
					return false;
				}
			}
		}
		//最后，遍历左边括号栈 以及 *号栈  一个一个弹出,如果左边括号栈弹出的下标值 > *号栈弹出的下标值  return false
		while (!left.isEmpty() && !xing.isEmpty()) {
			int leftIndex = left.pop();
			int xingIndex = xing.pop();
			if (leftIndex > xingIndex) {
				return false;
			}
		}
		//最后返回判断  左边括号栈是否为空即可
		return left.isEmpty();
	}

	//方式二：贪心算法
	public boolean checkValidString2(String s) {
		//定义未匹配的左括号数量的最小值和最大值 两个int值
		int maxLeft = 0;
		int minLeft = 0;
		//如果遇到左括号 那么最小值 跟最大值都 + 1；
		for (char ch : s.toCharArray()) {
			if (ch == '(') {
				minLeft++;
				maxLeft++;
			} else if (ch == ')') {//如果遇到右括号  那么最小值 跟最大值都 - 1；
				//当最小值小于0的时候，不应该继续减 应该保持0
				minLeft = Math.max(minLeft - 1, 0);
				maxLeft--;
				//未匹配的左括号数量，必须非负，如果最大值小于0的时候，说明没有左括号可以与右括号进行匹配，因此返回false
				if (maxLeft < 0) return false;
			} else {//如果遇到* 号  那么将最小值 -1  最大值+1；
				//当最小值小于0的时候，不应该继续减 应该保持0
				minLeft = Math.max(minLeft - 1, 0);
				maxLeft++;
			}
		}
		return minLeft == 0;
	}
}
