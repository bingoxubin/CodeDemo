//调整数组顺序使奇数位于偶数前面
//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

//思路：
//两次遍历，第一次把奇数加到list中，第二次把偶数加到list中，时间复杂度为O(n)。
//利用类似于插入排序的方法，但时间复杂度为O(n^2)。

package com.bingoabin.newcoder;

public class _13AdjustArrayOrder {
	public class Solution {
		public void reOrderArray(int[] array) {
			//1 2 3 4 5 6 7
			//1 3 5 7 2 4 6
			int[] result = new int[array.length];
			int j = 0;
			int m = 0;
			for (int i = 0; i < array.length; i++) {
				if (array[i] % 2 == 1) {
					result[j++] = array[i];
				}
			}
			for (int i = 0; i < array.length; i++) {
				if (array[i] % 2 == 0) {
					result[j++] = array[i];
				}
			}
			for (int temp = 0; temp < result.length; temp++) {
				array[m++] = result[temp];
			}
		}
	}

	public class Solution1 {
		public void reOrderArray(int[] array) {
			int oddIndex = -1;//已遍历的数中最后一个奇数的下标，默认为-1，当查询到第一个奇数时，自增即为0，然后把那个数一步一步挪动下标为0的地方
			int j = 0;
			int temp;
			for (int i = 0; i < array.length; i++) {
				if (array[i] % 2 == 1) {
					oddIndex++;//每查询到一个奇数，则最后一个奇数的下标前移
					j = i;
					while (j > oddIndex) { //替换到oddIndex下标为止
						temp = array[j];
						array[j] = array[j - 1];
						array[j - 1] = temp;
						j--;
					}
				}
			}

		}
	}

	public class Solution2 {
		public void reOrderArray(int[] array) {
			int index = -1;
			int temp = 0;
			int j = 0;
			for (int i = 0; i < array.length; i++) {
				if (array[i] % 2 == 1) {
					index++;
					j = i;
					temp = array[i];
					while (j > index) {
						array[j] = array[j - 1];
						j--;
					}
					array[index] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		Solution2 result = new _13AdjustArrayOrder().new Solution2();
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
		result.reOrderArray(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

	}
}













