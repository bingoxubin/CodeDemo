package com.bingoabin.algorithm.doublepoints;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/15 6:04 下午
 */
public class ThreeNumSum {
	//NC54 三数之和  https://www.nowcoder.com/practice/345e2ed5f81d4017bbb8cc6055b0b711?tpId=117&tqId=37751&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[-2,0,1,1,2]  返回：[[-2,0,2],[-2,1,1]]
	//分析：从无序的数组中，找出3个数的数组，使得数组之和为0，并且三元组必须按照升序排序，且不能包含重复的三元组
	//思路：通过双指针来求解，先固定第一个数，然后左指针指向第二个，右指针
	public static void main(String[] args) {
		int[] arr = {-2, 0, 1, 1, 2};
		System.out.println(threeSum(arr));
	}

	public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		//先进行排序
		Arrays.sort(num);
		//结果集
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		//边界情况
		//if(num.length < 3) return res;
		for (int i = 0; i < num.length - 2; i++) {
			//如果最左边的数大于0，直接可以退出了
			if (num[i] > 0) {
				break;
			}
			//如果最左边的，跟之前的一样，那么直接往后，再来
			if (i > 0 && num[i] == num[i - 1]) {
				continue;
			}
			//标记除了最左边的数字，定义首尾
			int left = i + 1, right = num.length - 1;
			while (left < right) {
				int sum = num[i] + num[left] + num[right];
				if (sum == 0) {
					res.add(new ArrayList(Arrays.asList(num[i], num[left], num[right])));
					//如果最后一个数字，跟下一次遍历的最后数字一样，过滤掉
					while (left < right && num[right] == num[right - 1]) {
						right--;
					}
					//如果中间一个数字，跟下一次遍历的中间数字一样，过滤掉
					while (left < right && num[left] == num[left + 1]) {
						left++;
					}
					right--;
					left++;
				} else if (sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}
		return res;
	}
}
