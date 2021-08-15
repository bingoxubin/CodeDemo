package com.bingoabin.algorithm.doublepoints;

/**
 * @Author: xubin34
 * @Date: 2021/8/15 11:41 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DeleteElementsInArrayEqual {
	//删除数组中的重复元素，保留一个
	//案例：{0,1,1,1,2,2,2,3,3,3,4,4,5,5} 返回 {0,1,2,3,4,5} 长度为 6
	//分析：删除数组中的重复元素，不能使用额外的空间
	//思路：通过双指针
	public static void main(String[] args) {
		int[] arr = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5};
		System.out.println(deleteEqualElementsInArray(arr));
	}

	//res计数 表示不重复的最后一个下标  i表示从1开始，不断往后移，如果碰到i下标值 跟 res下标值不一致，那么++res 并且把i的值复制给res的下一个位置
	public static int deleteEqualElementsInArray(int[] arr) {
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[res]) {
				arr[++res] = arr[i];
			}
		}
		return ++res;
	}
}
