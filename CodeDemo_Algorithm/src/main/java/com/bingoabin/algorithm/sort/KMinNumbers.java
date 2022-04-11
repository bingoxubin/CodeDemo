package com.bingoabin.algorithm.sort;

import java.util.ArrayList;

/**
 * @author xubin03
 * @date 2021/5/13 6:49 下午
 */
public class KMinNumbers {
	//NC119 最小的K个数  https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=117&tqId=37765&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[4,5,1,6,2,7,3,8] 4 输出：1,2,3,4
	//分析：一个乱序数组，求出最小的k个
	//思路：采用快排的思想，从小到大的排，当选中的那个值，交换位置正好在数组下表为k-1的时候，那么前面几个就是结果值，如果k>数组长度，返回空数组
	public static void main(String[] args) {
		int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
		System.out.println(GetLeastNumbers_Solution(arr,4));
	}

	public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (k > input.length || k < 0) {
			return res;
		}
		quickSort(input, 0, input.length - 1, k);
		for (int i = 0; i < k; i++) {
			res.add(input[i]);
		}
		return res;
	}

	public static void quickSort(int[] arr, int left, int right, int k) {
		if (left > right) {
			return;
		}
		//标记下最左边的值，作为比较的临界
		int temp = arr[left];
		//开始比较，左边的都大于temp，右边的都小于temp
		int i = left;
		int j = right;
		while (i < j) {
			while (i < j && arr[j] > temp) {
				j--;
			}
			while (i < j && arr[i] <= temp) {
				i++;
			}
			if (i < j) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		arr[left] = arr[i];
		arr[i] = temp;
		//刚好到达边界，即可退出
		if (i == k - 1) {
			return;
		}
		//递归调用
		quickSort(arr, left, i - 1, k);
		quickSort(arr, i + 1, right, k);
	}
}
