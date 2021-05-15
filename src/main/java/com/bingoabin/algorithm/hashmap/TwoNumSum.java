package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/5/14 11:59 下午
 */
public class TwoNumSum {
	//NC61 两数之和  https://www.nowcoder.com/practice/20ef0972485e41019e39543e8e895b7f?tpId=117&tqId=37756&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[3,2,4],6  返回：[2,3]
	//分析：数组中找出2个数之和为目标数值，结果显示为两个和的下标，下标从1开始，从小到大排序，确保有唯一解
	//思路：通过hashmap来求值
	public static void main(String[] args) {
		int[] arr = {3,2,4};
		System.out.println(Arrays.toString(twoSum(arr, 6)));
	}

	public static int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0;i<numbers.length;i++){
			if(map.containsKey(target - numbers[i])){
				return new int[]{map.get(target-numbers[i]),i+1};
			}
			map.put(numbers[i],i+1);
		}
		return new int[]{-1,-1};
	}
}
