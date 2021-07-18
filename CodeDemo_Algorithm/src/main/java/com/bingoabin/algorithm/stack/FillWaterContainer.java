package com.bingoabin.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/5/21 1:10 下午
 */
public class FillWaterContainer {
	//NC 128. 剩水容器  https://www.nowcoder.com/practice/31c1aed01b394f0b8b7734de0324e00f?tpId=117&tqId=37802&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[3,1,2,5,2,4]  返回5
	//分析：给一个数组，数组中的元素代表矩形的高度，在数组中所有凹陷的地方能存放多少水量
	//思路：方式一：暴力法 从下标为1的位置开始遍历，分别搜索左边的最大值，右边的最大值，然后求中间差值，累加计算结果
	//     方式二：动态规划 从左向右看数组，在每个位置上能得到的最大值，以及从右向左看，在每个位置上能得到的最大值，然后从第一个位置遍历到倒数第二个位置，每个位置上的取最小值即为最后的高度，减去原始高度
	//     方式三：栈 创建一个栈  不断将下标加入栈中，新加入节点，进行循环判断，如果新加入的节点的值大于栈顶的值，不断遍历弹出栈顶的值，弹出一个就加上这个点能盛的水量
	//     方式四：双指针 首先确定左右两边两个边界，比较左右两边取较小的一个，然后认定较小的一个为最大值，往后比较，如果小于这个最大值，那就加上差值
	public static void main(String[] args) {
		int[] arr = {3, 1, 2, 5, 2, 4};
		System.out.println(maxWater1(arr));
	}

	//方式一：暴力法 从下标为1的位置开始遍历，分别搜索左边的最大值，右边的最大值，然后求中间差值，累加计算结果  计算超时
	public static long maxWater0(int[] arr) {
		long res = 0;
		for (int i = 1; i < arr.length - 1; i++) {
			int left = arr[i];
			int right = arr[i];
			for (int j = 0; j < i; j++) {
				left = Math.max(left, arr[j]);
			}
			for (int j = i + 1; j < arr.length; j++) {
				right = Math.max(right, arr[j]);
			}
			res += Math.min(left, right) - arr[i];
		}
		return res;
	}

	//方式二：动态规划  从左向右看数组，在每个位置上能得到的最大值，以及从右向左看，在每个位置上能得到的最大值，然后从第一个位置遍历到倒数第二个位置，每个位置上的取最小值即为最后的高度，减去原始高度
	//          [3,1,2,5,2,4]
	//从左向右看   3,3,3,5,5,5
	//从右向左看   5,5,5,5,4,4
	public static long maxWater1(int[] arr) {
		int[] left = new int[arr.length];
		int[] right = new int[arr.length];
		long sum = 0;
		left[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			left[i] = Math.max(left[i - 1], arr[i]);
		}
		right[arr.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1], arr[i]);
		}
		for (int i = 1; i < arr.length - 1; i++) {
			int min = Math.min(left[i], right[i]);
			if (min > arr[i]) {
				sum += min - arr[i];
			}
		}
		return sum;
	}

	//方式三：栈  创建一个栈  不断将下标加入栈中，新加入节点，进行循环判断，如果新加入的节点的值大于栈顶的值，不断遍历弹出栈顶的值，弹出一个就加上这个点能盛的水量
	//[3,1,2,5,2,4]
	public static long maxWater2(int[] height) {
		Deque<Integer> stack = new LinkedList<Integer>();
		long res = 0;
		int cur = 0;
		while (cur < height.length) {
			while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
				int top = stack.pop();
				if (stack.isEmpty()) {
					break;
				}
				long distance = cur - stack.peek() - 1;
				long high = Math.min(height[cur], height[stack.peek()]) - height[top];
				res += distance * high;
			}
			stack.push(cur++);
		}
		return res;
	}

	//方式四：双指针 首先确定左右两边两个边界，比较左右两边取较小的一个，然后认定较小的一个为最大值，往后比较，如果小于这个最大值，那就加上差值
	//[3,1,2,5,2,4]
	public static long maxWater3(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int maxleft = 0;
		int maxright = 0;
		long res = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				if (height[left] > maxleft) {
					maxleft = height[left];
				} else {
					res += maxleft - height[left];
				}
				left++;
			} else {
				if (height[right] > maxright) {
					maxright = height[right];
				} else {
					res += maxright - height[right];
				}
				right--;
			}
		}
		return res;
	}
}
