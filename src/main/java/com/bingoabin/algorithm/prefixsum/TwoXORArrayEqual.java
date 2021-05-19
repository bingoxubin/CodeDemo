package com.bingoabin.algorithm.prefixsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xubin03
 * @date 2021/5/19 2:34 下午
 */
public class TwoXORArrayEqual {
	//Leetcode 1442. 形成两个异或相等数组的三元组数目  https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
	//样例：[2,3,1,6,7] 输出：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4) 结果为4
	//分析：求满足条件的三元组，比如(2,3,4) 得满足2~（3-1）的异或和  等于  3~4的异或和
	//思路：方式一：用前缀异或和的方式，求出结果，然后进行遍历求解  n^3
	//     方式二：可以得出总的三元组段内的异或和为0，计算每个异或和的值，用hashmap存储，如果相等计算统计结果
	public static void main(String[] args) {
		int[] arr = {2, 3, 1, 6, 7};
		System.out.println(countTriplets1(arr));
	}

	//原   2 3 1 6 7
	//后 0 2 1 0 6 1
	//证 (0,2,2)  2^3 = 1   1
	public static int countTriplets1(int[] arr) {
		int len = arr.length;
		int[] res = new int[len + 1];
		for (int i = 1; i <= len; i++) res[i] = res[i - 1] ^ arr[i - 1];
		int ans = 0;
		for (int i = 1; i <= len; i++) {
			for (int j = i + 1; j <= len; j++) {
				for (int k = j; k <= len; k++) {
					int a = res[j - 1] ^ res[i - 1];
					int b = res[k] ^ res[j - 1];
					if (a == b) {
						//System.out.println(i+" "+j+" "+k);
						ans++;
					}
				}
			}
		}
		return ans;
	}

	//用hashmap的方式，key表示为所求的异或值，value用list表示，表示求得改异或值的下标值
	//比如key 为0 表示所求异或值为0 ，value中有list含有2个数字2，5 表示2-5中间的所有数字比如（1，2，4，7）异或值为0，那么2-5中有几种情况呢，有(1,2,7)(1,4,7)
	public static int countTriplets(int[] arr) {
		int len = arr.length;
		int[] res = new int[len + 1];
		for (int i = 1; i <= len; i++) res[i] = res[i - 1] ^ arr[i - 1];
		int ans = 0;
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i <= len; i++) {
			List<Integer> list = map.getOrDefault(res[i], new ArrayList<>());
			for (Integer num : list) {
				ans += i - num - 1;
			}
			list.add(i);
			map.put(res[i], list);
		}
		return ans;
	}
}
