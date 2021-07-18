package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author xubin03
 * @date 2021/5/27 9:41 上午
 */
public class ProkerStraight {
	//NC63 扑克牌顺子  https://www.nowcoder.com/practice/762836f4d43d43ca9deb273b3de8e1f4?tpId=117&&tqId=37758&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：[6,0,2,0,4]   返回：true
	//分析：给一个数组，数组中代表0-13的数字，0可以代表任何数，问着5个数能否组成顺子
	//思路：方式一：进行排序，统计0的个数，如果有2个  那么最后一个数 - num[2] 不能超过5  而且不能有相同的两个数
	//     方式二：用hashset存储，如果有有相同的就return false  并统计出最小值 最大值 不能超过5 就返回true
	public static void main(String[] args) {
		int[] arr = {6, 0, 2, 0, 4};
		int[] arr1 = {1, 3, 2, 5, 4};
		System.out.println(IsContinuous(arr));
		System.out.println(IsContinuous1(arr1));
	}

	//方式一：进行排序，除开0的最小值  跟 最大值不能超过5  而且不能有相同的两个数（不包括0）
	public static boolean IsContinuous(int[] numbers) {
		Arrays.sort(numbers);
		int zero = 0;
		for (int i = 0; i < numbers.length - 1; i++) {
			//统计0的个数
			if (numbers[i] == 0) {
				zero++;
				//如果两个数相等 就返回false
			} else if (numbers[i] == numbers[i + 1]) {
				return false;
			}
		}
		return numbers[4] - numbers[zero] < 5;
	}

	//方式二：用hashset存储，如果有有相同的就return false  并统计出最小值 最大值 不能超过5 就返回true
	public static boolean IsContinuous1(int[] numbers) {
		int min = 14;
		int max = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int number : numbers) {
			if (number == 0) {
				continue;
			} else {
				min = Math.min(number, min);
				max = Math.max(number, max);
				if (set.contains(number)) {
					return false;
				}
				set.add(number);
			}
		}
		return max - min < 5;
	}
}
