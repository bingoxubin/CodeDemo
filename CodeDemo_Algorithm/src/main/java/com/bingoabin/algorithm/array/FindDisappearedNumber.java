package com.bingoabin.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/7/6 9:47
 */
public class FindDisappearedNumber {
	//Leetcode 448. 找到所有数组中消失的数字
	//示例：示例:
	//      输入:
	//      [4,3,2,7,8,2,3,1]
	//      输出:
	//      [5,6]
	//分析：给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
	//      找到所有在 [1, n] 范围之间没有出现在数组中的数字。
	//      您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
	//思路：
	public static void main(String[] args) {
		FindDisappearedNumber findDisappearedNumber = new FindDisappearedNumber();
		int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
		System.out.println(findDisappearedNumber.findDisappearedNumbers(nums));
	}

	public List<Integer> findDisappearedNumbers(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int index = Math.abs(nums[i]) - 1;
			if (nums[index] > 0) {
				nums[index] *= -1;
			}
		}
		System.out.println(Arrays.toString(nums));
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				res.add(i + 1);
			}
		}
		return res;
	}
}
