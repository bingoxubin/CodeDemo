package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/7/11 2:34
 */
public class HCitations {
	//LeetCode 274. H 指数
	//实例：输入：citations = [3,0,6,1,5] 输出：3
	//分析：数组表示n篇论文，数组中的值表示引用指数，其中如果有3个刚好>=3的值，那返回3
	//思路：通过排序，然后检验的方式
	public static void main(String[] args) {
		int[] arr = {3, 0, 6, 5, 1};
		System.out.println(hIndex(arr));
	}

	public static int hIndex(int[] citations) {
		Arrays.sort(citations);
		int len = citations.length;
		//边界情况，最少的引用次数都比长度大，那就返回长度即可
		if (citations[0] >= len) return len;
		//如果没有达到长度的值
		for (int i = 0; i < len - 1; i++) {
			//长度
			int temp = len - i - 1;
			if (citations[i] <= temp && citations[i + 1] >= temp) { //注意前一个是<=  反例{1,1,3}
				return temp;
			}
		}
		return 0;
	}
}
