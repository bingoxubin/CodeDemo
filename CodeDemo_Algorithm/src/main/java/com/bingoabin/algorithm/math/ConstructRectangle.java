package com.bingoabin.algorithm.math;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/10/23 11:34 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ConstructRectangle {
	//Leetcode 492. 构造矩形
	//示例：输入: 4
	//     输出: [2, 2]
	//     解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
	//分析：给定一个矩形的面积，求得他的长宽，并且要求长大于宽，而且他们之间的差距应该越小越好
	//思路：先通过area面积，求出方差，从方差进行遍历，不断减小1，如果能除尽，那么除尽出来的值就是长，遍历到得值就是宽
	public static void main(String[] args) {
		ConstructRectangle constructRectangle = new ConstructRectangle();
		System.out.println(Arrays.toString(constructRectangle.constructRectangle(15)));
	}

	public int[] constructRectangle(int area) {
		int sqrt = (int) Math.sqrt(area);
		for (int i = sqrt; i >= 1; i--) {
			if (area % i == 0) {
				return new int[]{area / i, i};
			}
		}
		return null;
	}
}
