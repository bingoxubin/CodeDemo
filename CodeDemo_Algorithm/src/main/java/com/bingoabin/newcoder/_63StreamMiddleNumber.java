//数据流中的中位数
// 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
// 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
//输入
//
// > 5,2,3,4,1,6,7,0,8
// > 输出
// > 5.0,3.5,3.0,3.5,3.0,3.5,4.0,3.5,4.0

package com.bingoabin.newcoder;

import java.util.ArrayList;

public class _63StreamMiddleNumber {

	public class Solution {
		ArrayList<Integer> list = new ArrayList<Integer>();

		//插入时先找出当前数字应该插在list的哪个位置并插入
		public void Insert(Integer num) {
			int index = binarySearch(list, num);
			list.add(index, num);
		}

		//取有序list里的中位数
		public Double GetMedian() {
			int length = list.size();
			if ((length & 1) == 0) {
				return (double) (list.get(length / 2 - 1) + list.get(length / 2)) / 2;
			} else {
				return (double) list.get(length / 2);
			}
		}

		//二分查找，返回目标数应该放置的下标
		public int binarySearch(ArrayList<Integer> list, int target) {
			int low = 0;
			int high = list.size() - 1;
			int mid = 0;
			while (low <= high) {
				mid = (low + high) / 2;
				if (list.get(mid) < target) {
					low = mid + 1;
				} else if (list.get(mid) > target) {
					high = mid - 1;
				} else {
					break;
				}
			}
			return mid;
		}
	}
}
