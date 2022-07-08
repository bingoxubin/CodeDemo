package com.bingoabin.algorithm.array;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/7/8 9:47
 */
public class DuplicateZeros {
	//Leetcode 1089. 复写零
	//示例：示例 1：
	//      输入：[1,0,2,3,0,4,5,0]
	//      输出：null
	//      解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
	//      示例 2：
	//      输入：[1,2,3]
	//      输出：null
	//      解释：调用函数后，输入的数组将被修改为：[1,2,3]
	//分析：给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
	//      注意：请不要在超过该数组长度的位置写入元素。
	//      要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
	//思路：
	public static void main(String[] args) {
		int[] arrs = {1, 0, 2, 3, 0, 4, 5, 0};
		DuplicateZeros duplicateZeros = new DuplicateZeros();
		duplicateZeros.duplicateZeros(arrs);
		System.out.println(Arrays.toString(arrs));
	}

	public void duplicateZeros(int[] arrs) {
		for (int i = 0; i < arrs.length; i++) {
			if (arrs[i] == 0 && i < arrs.length - 1) {
				for (int j = arrs.length - 2; j >= i + 1; j--) {
					arrs[j + 1] = arrs[j];
				}
				arrs[++i] = 0;
			}
		}
	}
}
