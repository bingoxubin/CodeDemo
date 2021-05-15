package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/5/15 7:45 下午
 */
public class AboveHalfInArray {
	//NC73 数组中出现次数超过一半的数字 https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=117&tqId=37770&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[1,2,3,2,2,2,5,4,2]  返回：2
	//分析：数组中找出出现次数超过数组长度一半的数字
	//思路：方法一:通过hashmap来统计出现的次数，然后过滤出超过一半的数字
	//     方法二:通过投票法，先投票再验证，一开始初始化票数vote为0，初始值num为0，如果票数vote为0，那么当前值赋值给num,如果当前值等于num，那么票数+1否则-1，求出num在进行验证
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
		System.out.println(MoreThanHalfNum_Solution1(arr));
	}

	//方式一：hashmap计数法
	public static int MoreThanHalfNum_Solution(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : array) {
			map.put(num, map.getOrDefault(num, 0) + 1);
			if (map.get(num) > array.length / 2) {
				return num;
			}
		}
		return -1;
	}

	//方式二：采用投票法
	public static int MoreThanHalfNum_Solution1(int[] array) {
		//初始化票数 记下得到正数票的值
		int vote = 0, num = 0;
		for (int val : array) {
			//票数为0的时候，把当前值复制给num
			if (vote == 0) num = val;
			//记下票数
			vote += num == val ? 1 : -1;
		}
		//验证num是否正确
		int count = 0;
		for (int val : array) {
			if (val == num) count++;
			if (count > array.length / 2) return num;
		}
		return -1;
	}
}
