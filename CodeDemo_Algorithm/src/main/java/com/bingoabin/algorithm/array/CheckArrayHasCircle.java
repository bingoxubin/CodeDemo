package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/8/7 1:17 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CheckArrayHasCircle {
	//Leetcode 457. 环形数组是否存在循环
	//示例：输入：nums = [2,-1,1,2,2] 输出：true
	//分析：给一个非0的环形数组，环形意思就是第一个数向左走一个是最右边，最后边一个数向右走一个就是最左边
	//     正数表示向右走，负数表示向左走，如果产生环返回true 没有环返回false
	//     注意：环中所有节点必须都是正数  或者都是负数，并且环中节点数 > 1
	//思路：
	public static void main(String[] args) {
		int[] arr = {2, -1, 1, 2, 2};
		CheckArrayHasCircle cahc = new CheckArrayHasCircle();
		System.out.println(cahc.circularArrayLoop1(arr));
		System.out.println(cahc.circularArrayLoop2(arr));
		System.out.println(cahc.circularArrayLoop3(arr));
		int[] arr1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, -5};
		int[] arr2 = {2, -1, 1, -2, -2};
		System.out.println(cahc.circularArrayLoop4(arr2));
	}

	//方式一：进行模拟，从第一个元素开始检测
	//      判断第一个数的正负，然后记下正负值，并且计算下一个数的下标，如果正负值不一致返回false
	//      如果相等，就证明有循环了，返回k是否大于1
	//      否则当前值替换到next，k++,继续探索
	public boolean circularArrayLoop1(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			//k的作用是 如果有环 但是不是以i为循环体的，判断k是否大于数组长度，作为标识
			if (check(i, nums, 1)) return true;
		}
		return false;
	}

	public boolean check(int start, int[] nums, int k) {
		int len = nums.length;
		int cur = start;
		//判断当前值的正负 情况
		boolean flag = nums[cur] > 0;
		while (true) {
			//如果有环，但是cur不是其中的循环体，返回false
			if (k > len) return false;
			//求出下一个节点下标
			int next = ((cur + nums[cur]) % len + len) % len;
			//如果一开始的正负值  跟下一个节点的正负值不一致  返回false
			if (flag && nums[next] < 0) return false;
			if (!flag && nums[next] > 0) return false;
			//如果当前值 跟 初始值相等，判断循环体中的个数是否 大于1
			if (next == start) return k > 1;
			//当前值替换为cur 继续想下探寻
			cur = next;
			k++;
		}
	}

	//方式二：快慢指针方式
	public boolean circularArrayLoop2(int[] nums) {
		//开始循环
		for (int i = 0; i < nums.length; i++) {
			//定义快慢节点的值
			int slow = i;
			int fast = next(nums, slow);
			//如果快指针走两步，满指针走一步，三个点方向一致，继续，如果不一致下一个
			while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
				//如果slow 跟 fast相同 判断是不是单个节点循环
				if (slow == fast) {
					if (slow != next(nums, slow)) {
						return true;
					} else {
						break;
					}
				}
				slow = next(nums, slow);
				fast = next(nums, next(nums, fast));
			}
		}
		return false;
	}

	//求出下一个节点下标
	public int next(int[] nums, int cur) {
		int len = nums.length;
		return (((cur + nums[cur]) % len) + len) % len;
	}

	//方式三：快慢指针的优化方式
	public boolean circularArrayLoop3(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 0) {
				continue;
			}
			int slow = i, fast = next(nums, i);
			// 判断非零且方向相同
			while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
				if (slow == fast) {
					if (slow != next(nums, slow)) {
						return true;
					} else {
						break;
					}
				}
				slow = next(nums, slow);
				fast = next(nums, next(nums, fast));
			}
			int add = i;
			while (nums[add] * nums[next(nums, add)] > 0) {
				int tmp = add;
				add = next(nums, add);
				nums[tmp] = 0;
			}
		}
		return false;
	}

	//讨论自己的实现方式 双指针
	public boolean circularArrayLoop4(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int slow = i;
			int fast = next(nums, next(nums, slow));
			while (nums[slow] * nums[next(nums, slow)] > 0 && nums[slow] * nums[fast] > 0) {
				if (slow == fast) {
					if (slow != next(nums, slow)) {
						return true;
					} else {
						break;
					}
				}
				slow = next(nums, slow);
				fast = next(nums, next(nums, fast));
			}
		}
		return false;
	}
}
