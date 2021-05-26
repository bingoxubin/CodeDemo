package com.bingoabin.algorithm.binarysearch;

/**
 * @author xubin03
 * @date 2021/5/26 12:41 下午
 */
public class MissingNum {
	//NC101 缺失数字 https://www.nowcoder.com/practice/9ce534c8132b4e189fd3130519420cde?tpId=117&&tqId=37825&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：[0,1,2,3,4,5,7] 返回 6
	//分析：给一个从0-n+1的数组，一共n个数，其中少一个数，求出少的这个数
	//思路：方式一：位运算  将数组中的数和1-n+1中的所有数进行异或运算，最后得出的数就是结果
	//     方式二:数学法  求和的方式做差 得出结果
	//     方式三:差值法  看后面一个数是否比前面一个大1
	//     方式四:二分法  二分法  记下范围为0 ~ a.length - 1,如果下标的值 = 元素的值 left = mid + 1,如果下标的值 < 元素的值  right = mid - 1; 不存在下标值 > 元素值的情况
	public static void main(String[] args) {
		int[] arr = {0, 1, 2, 3, 4, 5, 7};
		System.out.println(solve1(arr));
		System.out.println(solve2(arr));
		System.out.println(solve3(arr));
		System.out.println(solve4(arr));
	}

	//方式一：位运算  将数组中的数和1-n+1中的所有数进行异或运算，最后得出的数就是结果
	public static int solve1(int[] a) {
		int res = a.length;
		for (int i = 0; i < a.length; i++) {
			res ^= a[i];
			res ^= i;
		}
		return res;
	}

	//方式二:数学法  求和的方式做差 得出结果
	public static int solve2(int[] a) {
		int sum = 0;
		for (int num : a) sum += num;
		return (1 + a.length) * a.length / 2 - sum;
	}

	//方式三:差值法  看后面一个数是否比前面一个大1
	public static int solve3(int[] a) {
		if (a[0] != 0) return 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] - a[i - 1] != 1) {
				return a[i] - 1;
			}
		}
		return a.length;
	}

	//方式四:二分法  记下范围为0 ~ a.length - 1,如果下标的值 = 元素的值 left = mid + 1,如果下标的值 < 元素的值  right = mid - 1; 不存在下标值 > 元素值的情况
	public static int solve4(int[] a) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (a[mid] == mid) {
				left = mid + 1;
			} else if (a[mid] > mid) {
				right = mid - 1;
			}
		}
		return left;
	}
}
