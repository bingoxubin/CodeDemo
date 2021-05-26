//数组中的逆序对
//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007

//题目保证输入的数组中没有的相同的数字数据范围：	对于%50的数据,size<=10^4	对于%75的数据,size<=10^5	对于%100的数据,size<=2*10^5
//1,2,3,4,5,6,7,0
//7

//暴力枚举
//归并排序思想：
// 	归并时统计子序列中的逆序队数。假设nums[0]~nums[mid]和nums[mid+1]~nums[high]之中的遍历指针分别为i,j。
// 	若nums[i]<=nums[j]，则为（nums[i],nums[j]）为正序对。
// 	否则，nums[i]~nums[mid]分别与nums[j]形成逆序队。一共mid-i个。

package com.bingoabin.newcoder;

public class _35ReverseOrderArray {
	public class Solution {
		public int InversePairs(int[] array) {
			if (array == null || array.length == 0) {
				return 0;
			}
			int[] copy = new int[array.length];
			for (int i = 0; i < array.length; i++) {
				copy[i] = array[i];
			}
			int count = InversePairsCore(array, copy, 0, array.length - 1);//数值过大求余
			return count;

		}

		private int InversePairsCore(int[] array, int[] copy, int low, int high) {
			if (low == high) {
				return 0;
			}
			int mid = (low + high) >> 1;
			int leftCount = InversePairsCore(array, copy, low, mid) % 1000000007;
			int rightCount = InversePairsCore(array, copy, mid + 1, high) % 1000000007;
			int count = 0;
			int i = mid;
			int j = high;
			int locCopy = high;
			while (i >= low && j > mid) {
				if (array[i] > array[j]) {
					count += j - mid;
					copy[locCopy--] = array[i--];
					if (count >= 1000000007)//数值过大求余
					{
						count %= 1000000007;
					}
				} else {
					copy[locCopy--] = array[j--];
				}
			}
			for (; i >= low; i--) {
				copy[locCopy--] = array[i];
			}
			for (; j > mid; j--) {
				copy[locCopy--] = array[j];
			}
			for (int s = low; s <= high; s++) {
				array[s] = copy[s];
			}
			return (leftCount + rightCount + count) % 1000000007;
		}
	}

	public class Solution2 {
		int count = 0;

		public int InversePairs(int[] array) {
			if (array == null || array.length == 0) {
				return 0;
			}
			mergeSort(array, 0, array.length - 1);
			return count;
		}

		public void mergeSort(int[] nums, int low, int high) {
			int mid = 0;
			if (low < high) {
				mid = (low + high) / 2;
				mergeSort(nums, low, mid);
				mergeSort(nums, mid + 1, high);
				merge(nums, low, mid, high);
			}
		}

		public void merge(int[] nums, int low, int mid, int high) {
			int i = low, j = mid + 1, k = 0;
			int[] temp = new int[high - low + 1];
			while (i <= mid && j <= high) {
				if (nums[i] <= nums[j]) {
					temp[k++] = nums[i++];
				} else {
					temp[k++] = nums[j++];
					//这是因为若第num[i]个大于num[j]，则i之后mid之前的数都会大于nums[j](非递减序列),
					// 而在上次的if中i多加了1，所以要再加上。
					count = (count + mid - i + 1) % 1000000007;
				}
			}
			while (i <= mid) {
				temp[k++] = nums[i++];
			}
			while (j <= high) {
				temp[k++] = nums[j++];
			}
			for (i = 0; i < temp.length; i++) {
				nums[low + i] = temp[i];
			}
		}
	}
}
