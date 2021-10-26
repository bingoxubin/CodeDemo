package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author: xubin34
 * @Date: 2021/10/26 9:50 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NextGreaterElement {
	//Leetcode 496. 下一个更大元素 I
	//示例：输入: nums1 = [4,1,2], nums2 = [1,3,4,2].   输出: [-1,3,-1]
	//分析：给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。
	//     请你找出 nums1中每个元素在nums2中的下一个比其大的值。
	//     nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。
	//思路：用单调栈的思路
	public static void main(String[] args) {
		int[] nums1 = {4, 1, 2};
		int[] nums2 = {1, 3, 4, 2};
		NextGreaterElement nextGreaterElement = new NextGreaterElement();
		System.out.println(Arrays.toString(nextGreaterElement.nextGreaterElement(nums1, nums2)));
	}

	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Deque<Integer> stack = new LinkedList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums2.length; i++) {
			while (!stack.isEmpty() && stack.peek() < nums2[i]) {
				map.put(stack.pop(), nums2[i]);
			}
			stack.push(nums2[i]);
		}
		while (!stack.isEmpty()) {
			map.put(stack.pop(), -1);
		}
		for (int i = 0; i < nums1.length; i++) {
			nums1[i] = map.get(nums1[i]);
		}
		return nums1;
	}

	//自己实现一遍
	public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
		Deque<Integer> stack = new LinkedList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<>();
		//从nums2数组的第一个数开始遍历
		for (int i = 0; i < nums2.length; i++) {
			//如果栈不为空，并且将要放入的值，比栈中的值要大的话，表示栈中下一个最大值就是将要放进去的值，用map进行记录
			while (!stack.isEmpty() && stack.peek() < nums2[i]) {
				map.put(stack.pop(), nums2[i]);
			}
			//否则放入到栈中
			stack.push(nums2[i]);
		}
		//栈中最后还剩的一些值，都是没有下一个最大值的
		while (!stack.isEmpty()) {
			map.put(stack.pop(), -1);
		}
		//将nums1中的值用map中的对应value进行替换
		for (int i = 0; i < nums1.length; i++) {
			nums1[i] = map.get(nums1[i]);
		}
		return nums1;
	}
}
