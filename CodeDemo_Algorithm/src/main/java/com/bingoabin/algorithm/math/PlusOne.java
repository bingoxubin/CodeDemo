package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/10/21 9:43 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class PlusOne {
	//Leetcode 66. 加一
	//示例：输入：digits = [4,3,2,1]
	//     输出：[4,3,2,2]
	//     解释：输入数组表示数字 4321。
	//分析：给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。你可以假设除了整数 0 之外，这个整数不会以零开头。
	//思路：从数组的末尾开始遍历，如果小于9那么加1 返回   如果是9的话  那么就变为0  往前移  最后还是9的话  那么就数组长度加1
	public static void main(String[] args) {
		int[] arr = {9, 9, 9, 9};
		PlusOne plusOne = new PlusOne();
		System.out.println(plusOne.plusOne(arr));
	}

	public int[] plusOne(int[] digits) {
		int len = digits.length;
		for (int i = len - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		int[] res = new int[len + 1];
		res[0] = 1;
		return res;
	}
}
