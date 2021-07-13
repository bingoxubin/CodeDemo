//最小的K个数
//输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

//思路：
//先排序再取出前k个数（此处练习顺便写了快排）

//对上述进行优化，由于每进行一趟快排，key前面的值小于key，key后面的值小于key。如果key前面的元素个数大于k个，则在前半部分继续查找。(AC了，有时间我再验证一下TODO)

//利用堆排序。

package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class _29MinNumberK {
	public class Solution {
		public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
			ArrayList<Integer> al = new ArrayList<Integer>();
			if (k > input.length) {
				return al;
			}
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < input.length - i - 1; j++) {
					if (input[j] < input[j + 1]) {
						int temp = input[j];
						input[j] = input[j + 1];
						input[j + 1] = temp;
					}
				}
				al.add(input[input.length - i - 1]);
			}
			return al;
		}
	}

	public class Solution1 {
		public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int index = 0;
			if (input == null || input.length < k || k <= 0) {
				return list;
			} else {
				//quickSort(input,0,input.length-1);
				Arrays.sort(input);
				while (index < k) {
					list.add(input[index++]);
				}
				return list;
			}
		}

		public void quickSort(int[] nums, int low, int high) {
			if (low < high) {
				int p = oneFastSort(nums, low, high);
				quickSort(nums, low, p - 1);
				quickSort(nums, p + 1, high);
			}
		}

		public int oneFastSort(int[] nums, int low, int high) {
			int flag = 0;
			int key = nums[low];

			while (low < high) {
				if (flag == 0) {
					if (nums[high] > key) {
						high--;
					} else {
						nums[low++] = nums[high];
						flag = 1;
					}
				} else {
					if (nums[low] < key) {
						low++;
					} else {
						nums[high--] = nums[low];
						flag = 0;
					}
				}
			}
			nums[low] = key;
			return low;
		}
	}

	public class Solution2 {
		public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int index = 0;
			if (input == null || input.length < k || k <= 0) {
				return list;
			} else {
				quickSort(input, 0, input.length - 1, -1, k);
				while (index < k) {
					list.add(input[index++]);
				}
				return list;
			}
		}

		public void quickSort(int[] nums, int low, int high, int p, int k) {
			p = oneFastSort(nums, low, high);
			if (low < high && p > k) {
				quickSort(nums, low, p - 1, p, k);
			} else if (low < high && p < k) {
				quickSort(nums, p + 1, high, p, k);
			} else if (p == k) {
				return;
			}
		}

		public int oneFastSort(int[] nums, int low, int high) {
			int flag = 0;
			int key = nums[low];

			while (low < high) {
				if (flag == 0) {
					if (nums[high] > key) {
						high--;
					} else {
						nums[low++] = nums[high];
						flag = 1;
					}
				} else {
					if (nums[low] < key) {
						low++;
					} else {
						nums[high--] = nums[low];
						flag = 0;
					}
				}
			}
			nums[low] = key;
			return low;
		}
	}
}
