package com.bingoabin.algorithm.array;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/15 6:34 下午
 */
public class MergeTwoArray {
	//NC22 合并两个有序数组 https://www.nowcoder.com/practice/89865d4375634fc484f3a24b7fe65665?tpId=117&tqId=37727&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[1,8,9,0,0,0] 3 [4,5,10],3  返回：[1,4,5,8,9,10]
	//分析：两个有序数组，进行合并到第一个数组中
	//思路：从第一个数组的最后一个空位置开始填起，如果一个数组到头了就终止，最后判断填到最后如果第二个数组到头了说明填完了，如果第二个数组没到头，就把第二个数组copy到第一个数组即可
	public static void main(String[] args) {
		int[] a = {1, 8, 9, 0, 0, 0};
		int[] b = {4, 5, 10};
		merge(a, 3, b, 3);
		System.out.println(Arrays.toString(a));
	}

	public static void merge(int A[], int m, int B[], int n) {
		//A数组的下标最后值
		int index = A.length - 1;
		//分别切换为数组中有值的下标最后值
		m = m - 1;
		n = n - 1;
		while (m >= 0 && n >= 0) {
			//从后比较A B数组值，大的拷贝到A的最后位置
			A[index--] = A[m] > B[n] ? A[m--] : B[n--];
		}
		//如果B还没有拷贝完，就把B的拷贝到A中，如果B拷贝完，说明A中已经完全了，不要再拷贝了，因为数据已经在A里面了
		if (n >= 0) {
			System.arraycopy(B, 0, A, 0, index + 1);
		}
	}
}
