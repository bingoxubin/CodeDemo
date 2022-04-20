package com.bingoabin.algorithm.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2022/4/20 14:41
 */
public class GetRainWater {
	//Leetcode 42. 接雨水
	//示例：输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
	//      输出：6
	//      解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
	//分析：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
	//思路：
	public static void main(String[] args) {
		int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
		GetRainWater getRainWater = new GetRainWater();
		System.out.println(getRainWater.trap(height));
		System.out.println(getRainWater.trap1(height));
		System.out.println(getRainWater.trap2(height));
	}

	//方式一：动态规划
	public int trap(int[] height) {
		System.out.println(Arrays.toString(height));
		int sum = 0;
		int[] left = new int[height.length];
		int[] right = new int[height.length];
		for (int i = 1; i < height.length; i++) {
			left[i] = Math.max(left[i - 1], height[i - 1]);
		}
		System.out.println(Arrays.toString(left));
		for (int i = height.length - 2; i > 0; i--) {
			right[i] = Math.max(right[i + 1], height[i + 1]);
		}
		System.out.println(Arrays.toString(right));
		for (int i = 1; i < height.length - 1; i++) {
			int min = Math.min(left[i], right[i]);
			if (min > height[i]) {
				sum += (min - height[i]);
			}
		}
		return sum;
	}

	//方式二：单调栈
	public int trap1(int[] height) {
		Deque<Integer> stack = new LinkedList<>();
		int cur = 0;
		int sum = 0;
		while (cur < height.length) {
			while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
				int top = stack.pop();
				if (stack.isEmpty()) break;
				int distance = cur - stack.peek() - 1;
				int high = Math.min(height[cur], height[stack.peek()]) - height[top];
				sum += distance * high;
			}
			stack.push(cur++);
		}
		return sum;
	}

	//方式三：双指针
	public int trap2(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int sum = 0;
		int maxLeft = 0;
		int maxRight = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				if (height[left] > maxLeft) {
					maxLeft = height[left];
				} else {
					sum += maxLeft - height[left];
				}
				left++;
			} else {
				if (height[right] > maxRight) {
					maxRight = height[right];
				} else {
					sum += maxRight - height[right];
				}
				right--;
			}
		}
		return sum;
	}
}
