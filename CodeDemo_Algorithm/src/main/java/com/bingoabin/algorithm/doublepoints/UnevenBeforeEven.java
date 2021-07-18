package com.bingoabin.algorithm.doublepoints;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/26 10:25 下午
 */
public class UnevenBeforeEven {
	//NC77 调整数组顺序使奇数位于偶数前面  https://www.nowcoder.com/practice/ef1f53ef31ca408cada5093c8780f44b?tpId=117&&tqId=37776&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：输入：[1,2,3,4] 返回值：[1,3,2,4]
	//分析：给你一个数组，将数组中的奇数放到偶数的左边，偶数放到后半部分
	//思路：首先定义index 为 -1 表示当前为奇数的最后一个位置，然后从头开始遍历数组，当数组的值为奇数时，index++（表示这个位置开始到i-1的值为偶数），然后记录下下标为i的值，前面的后移，交换即可
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		System.out.println(Arrays.toString(reOrderArray(arr)));
	}

	public static int[] reOrderArray(int[] array) {
		int index = -1;
		for(int i = 0;i<array.length;i++){
			if(array[i] % 2 == 1){
				//标记偶数开始的位置
				index++;
				//记录下当前奇数位置以及值
				int j = i;
				int temp = array[i];
				//将偶数后移
				while(j > index){
					array[j] = array[j-1];
					j--;
				}
				//将奇数放到前面
				array[index] = temp;
			}
		}
		return array;
	}
}
