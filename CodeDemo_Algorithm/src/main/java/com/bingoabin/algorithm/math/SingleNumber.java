package com.bingoabin.algorithm.math;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/10/30 12:27 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SingleNumber {
	//Leetcode 260. 只出现一次的数字 III
	//示例：给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
	//分析：输入：nums = [1,2,1,3,2,5]   输出：[3,5]   解释：[5, 3] 也是有效的答案。
	//思路：方式一：通过hashmap计数
	//     方式二：通过异或来进行解决
	public static void main(String[] args) {
		int[] arr = {1, 2, 1, 3, 2, 5};
		SingleNumber singleNumber = new SingleNumber();
		System.out.println(Arrays.toString(singleNumber.singleNumber1(arr)));
		System.out.println(Arrays.toString(singleNumber.singleNumber2(arr)));
	}

	//方式一：用hashmap计数
	public int[] singleNumber1(int[] nums) {
		//实现nums中统计个数
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		//结果集
		int[] res = new int[2];
		//遍历map中，统计次数为1的
		int index = 0;
		for (Integer key : map.keySet()) {
			if (map.get(key) == 1) {
				res[index++] = key;
			}
		}
		return res;
	}

	//方式二：用异或的方式
	public int[] singleNumber2(int[] nums) {
		//先统计，所有数字的异或和
		int sum = 0;
		for (int num : nums) {
			sum ^= num;
		}
		//从异或和中，随机取一个不一样的二进制位，进行记下来
		int index = -1;
		for (int i = 0; i <= 31 && index == -1; i++) {
			if (((sum >> i) & 1) == 1) {
				index = i;
			}
		}
		//将数组中的所有数，按照index的位置的二进制不同，进行分别异或求和，得到最终的值
		int[] res = new int[2];
		for (int num : nums) {
			if (((num >> index) & 1) == 1) {
				res[0] ^= num;
			} else {
				res[1] ^= num;
			}
		}
		return res;
	}
}
