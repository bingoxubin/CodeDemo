//和为S的两个数字
//输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

//对应每个测试案例，输出两个数，小的先输出。

//例如：1,2,3,4,5,6    sum = 6；
// 分析可知：这两个数相等时乘积最大，即3*3=9的情况最大，最小的情况是用最小的乘最大，即1*5=5
// 设置高低指针，high、low，先固定low指针，然后将high指针自减，当两数之和为sum时，结束循环
// （注意需要跳出所有循环）。若high走完一遍还不满足，则low指针自增。

package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class _42TwoNumberSum {
	public class Solution {
		public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
			ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				ArrayList<Integer> list = new ArrayList<>();
				for (int j = i + 1; j < array.length; j++) {
					if (array[i] + array[j] == sum) {
						list.add(array[i]);
						list.add(array[j]);
						list.add(array[i] * array[j]);
					}
				}
				if (!list.isEmpty())
					lists.add(list);
			}
			if (lists.size() == 0) return new ArrayList<Integer>();
			//if(lists.size() ==1) return lists.get(0);
			Collections.sort(lists, new Comparator<ArrayList<Integer>>() {

				@Override
				public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
					return o1.get(2).compareTo(o2.get(2));

				}
			});

			ArrayList<Integer> result = new ArrayList<>();
			ArrayList<Integer> temp = lists.get(0);
			for (int i = 0; i < temp.size() - 1; i++) {
				result.add(temp.get(i));
			}

			return result;
		}
	}

	public class Solution1 {
		public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int low = 0; low < array.length; low++) {
				for (int high = array.length - 1; high >= low; high--) {
					if (array[low] + array[high] == sum) {
						list.add(array[low]);
						list.add(array[high]);
						break;
					}
				}
				//得到第一组结果则跳出，此时两数乘积最小。
				if (list.size() > 0) {
					break;
				}
			}
			return list;
		}
	}
}
