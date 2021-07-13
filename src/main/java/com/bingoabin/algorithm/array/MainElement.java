package com.bingoabin.algorithm.array;

/**
 * @author bingoabin
 * @date 2021/7/9 2:30
 */
public class MainElement {
	//LeetCode 面试题 17.10. 主要元素
	//示例：输入：[1,2,5,9,5,9,5,5,5] 输出：5
	//分析：数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。
	//思考：采用投票法
	public static void main(String[] args) {
		int[] arr = {1, 2, 5, 9, 5, 9, 5, 5, 5};
		System.out.println(majorityElement(arr));
	}

	//投票法，再判断，先定义结果值res，以及票数vote，如果vote==0 那么res = num 否则res += num == res？1：-1；
	public static int majorityElement(int[] nums) {
		int res = -1;
		int vote = 0;
		//求出结果res  但是不一定是正确的
		for (int num : nums) {
			if (vote == 0) res = num;
			vote += num == res ? 1 : -1;
		}
		//再次判断是否正确
		int count = 0;
		for (int num : nums) {
			if (num == res) count++;
			if (count > nums.length / 2) return res;
		}
		return -1;
	}
}
