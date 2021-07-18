package com.bingoabin.algorithm.sort;

/**
 * @author xubin03
 * @date 2021/5/13 7:29 下午
 */
public class FindKthMax {
	//NC88 寻找第K大  https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf?tpId=117&tqId=37791&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：输入[1,3,5,2,2],5,3 输出：2
	//分析：给一个长度为n的数组，找出第k大的数
	//思路：同样采用快排的方式，数组长度为n，求第k大，也就是找下标为n-k位置，即可
	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 2, 2};
		System.out.println(findKth(arr, arr.length, 3));
	}

	public static int findKth(int[] a, int n, int k) {
		if (k < 1 || k > n) {
			return -1;
		}
		quickSort(a, 0, a.length - 1, k);
		return a[n - k];
	}

	public static void quickSort(int[] arr, int left, int right, int k) {
		//退出条件
		if (left > right) {
			return;
		}
		//标记下最左边的值
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
		if (i == arr.length - k) {
			return;
		}
		//递归调用
		quickSort(arr, left, i - 1, k);
		quickSort(arr, i + 1, right, k);
	}
}
