//把数组排成最小的数
//输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

//两个数组拼成一个只有两种情况，如32和321只能拼成32321和32132，我们只要比较这两个数谁大即可。当然也可以直接调用字典序比较。

package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class _32ArraySortMin {
	public class Solution {
		public String PrintMinNumber(int[] numbers) {
			int n;
			String s = "";
			ArrayList<Integer> list = new ArrayList<Integer>();
			n = numbers.length;

			for (int i = 0; i < n; i++) {
				list.add(numbers[i]);//将数组放入arrayList中
			}
			//实现了Comparator接口的compare方法，将集合元素按照compare方法的规则进行排序
			Collections.sort(list, new Comparator<Integer>() {

				@Override
				public int compare(Integer str1, Integer str2) {
					// TODO Auto-generated method stub
					String s1 = str1 + "" + str2;
					String s2 = str2 + "" + str1;

					return s1.compareTo(s2);
				}
			});

			for (int j : list) {
				s += j;
			}
			return s;
		}
	}

	public class Solution1 {
		public String PrintMinNumber(int[] numbers) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < numbers.length; i++) {
				list.add(numbers[i]);
			}

			//自定义比较器
			Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					String str1 = o1 + "" + o2;
					String str2 = o2 + "" + o1;
					return str1.compareTo(str2); ////若str2的字典序位于str1之前，返回一个负整数，相等返回0，否则返回一个正整数。即判断str1的字典序是否先于str2,先于返回正整数。。。。
				}
			});

			String result = "";
			for (int num : list) {
				result += num;
			}

			return result;
		}
	}
}
