package com.bingoabin.algorithm.hashmap;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2021/10/6 1:15 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ThreeMaxNum {
	//Leetcode 414. 第三大的数
	//示例：输入：[2, 2, 3, 1]
	//     输出：1
	//     解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
	//     此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
	//分析：给一个数组中 找出第三大的数，给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
	//思路：方式一：用三个变量进行实现，如果大于最大的，那么将中间的给最小的，最大的给中间的，当前值给最大的，进行遍历，排除等于one two three的情况，并且要用long来定义one two three，需要排除Integer.MIN_VALUE的情况
	//     方式二：用优先队列，先将数组用hashset进行过滤存储，然后放入小根堆中，如果小根堆的size > 3，那么就剔除一个
	//     方式三：用treeset的方式，如果size > 3,移除第一个
	//     方式四：用排序的方式，从后往前，再用hashset接一下，统计不同的次数，如果是三次，那么就返回当前值
	public static void main(String[] args) {
		int[] arr = {2, 2, 3, 1};
		ThreeMaxNum res = new ThreeMaxNum();
		System.out.println(res.thirdMax1(arr));
		System.out.println(res.thirdMax2(arr));
		System.out.println(res.thirdMax3(arr));
		System.out.println(res.thirdMax4(arr));
		int[] arr1 = {1, 2, -2147483648};
		System.out.println(res.thirdMax2(arr1));
	}

	//方式一：用三个变量进行实现，如果大于最大的，那么将中间的给最小的，最大的给中间的，当前值给最大的，进行遍历，排除等于one two three的情况，并且要用long来定义one two three，需要排除Integer.MIN_VALUE的情况
	public int thirdMax1(int[] nums) {
		long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
		for (int num : nums) {
			if (num == a || num == b || num == c) continue;
			if (num > a) {
				c = b;
				b = a;
				a = num;
			} else if (num > b) {
				c = b;
				b = num;
			} else if (num > c) {
				c = num;
			}
		}
		return c == Long.MIN_VALUE ? (int) a : (int) c;
	}

	//方式二：用优先队列，先将数组用hashset进行过滤存储，然后放入小根堆中，如果小根堆的size > 3，那么就剔除一个
	public int thirdMax2(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		int max = 0;
		for (int num : nums) {
			if (num > max) max = num;
			if (!set.add(num)) continue;
			queue.offer(num);
			if (queue.size() > 3) {
				queue.poll();
			}
		}
		return queue.size() == 3 ? queue.peek() : max;
	}

	//方式三：用treeset的方式，如果size > 3,移除第一个
	public int thirdMax3(int[] nums) {
		TreeSet<Integer> tree = new TreeSet<>();
		for (int num : nums) {
			tree.add(num);
			if (tree.size() > 3) tree.remove(tree.first());
		}
		return tree.size() == 3 ? tree.first() : tree.last();
	}

	//方式四：用排序的方式，从后往前，再用hashset接一下，统计不同的次数，如果是三次，那么就返回当前值
	public int thirdMax4(int[] nums) {
		Arrays.sort(nums);
		HashSet<Integer> set = new HashSet<>();
		for (int i = nums.length - 1; i >= 0; i--) {
			if (!set.contains(nums[i])) {
				if (set.size() == 2) {
					return nums[i];
				} else {
					set.add(nums[i]);
				}
			}
		}
		return nums[nums.length - 1];
	}
}
