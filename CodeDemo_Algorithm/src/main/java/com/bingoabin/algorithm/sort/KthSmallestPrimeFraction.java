package com.bingoabin.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/11/29 1:12 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class KthSmallestPrimeFraction {
	//Leetcode 786. 第 K 个最小的素数分数
	//分析：输入：arr = [1,2,3,5], k = 3
	//     输出：[2,5]
	//     解释：已构造好的分数,排序后如下所示:
	//     1/5, 1/3, 2/5, 1/2, 3/5, 2/3
	//     很明显第三个最小的分数是 2/5
	//示例：给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数组成，且其中所有整数互不相同。
	//     对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
	//     那么第k个最小的分数是多少呢? 以长度为 2 的整数数组返回你的答案, 这里answer[0] == arr[i]且answer[1] == arr[j] 。
	//思路：自定义排序
	public static void main(String[] args) {
		KthSmallestPrimeFraction kthSmallestPrimeFraction = new KthSmallestPrimeFraction();
		int[] arr = {1, 2, 3, 5};
		System.out.println(Arrays.toString(kthSmallestPrimeFraction.kthSmallestPrimeFraction(arr, 3)));
	}

	public int[] kthSmallestPrimeFraction(int[] arr, int k) {
		//创建所有结果集，收集所有可能的分数情况
		List<int[]> res = new ArrayList<>();
		//数组的长度
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				res.add(new int[]{arr[i], arr[j]});
			}
		}
		//自定义排序
		Collections.sort(res, (x, y) -> (x[0] * y[1] - x[1] * y[0]));
		return res.get(k - 1);
	}
}
