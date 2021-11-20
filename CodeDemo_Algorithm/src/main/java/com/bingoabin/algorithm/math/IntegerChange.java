package com.bingoabin.algorithm.math;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xubin34
 * @Date: 2021/11/19 10:13 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class IntegerChange {
	//Leetcode 397. 整数替换
	//示例：输入：n = 8
	//     输出：3
	//     解释：8 -> 4 -> 2 -> 1
	//     输入：n = 7
	//     输出：4
	//     解释：7 -> 8 -> 4 -> 2 -> 1 或 7 -> 6 -> 3 -> 2 -> 1
	//分析：给定一个正整数n ，你可以做如下操作：
	//     如果n是偶数，则用n / 2替换n 。
	//     如果n是奇数，则可以用n + 1或n - 1替换n 。
	//     n变为 1 所需的最小替换次数是多少？
	//思路：
	public static void main(String[] args) {
		IntegerChange integerChange = new IntegerChange();
		System.out.println(integerChange.integerReplacement1(7));
		System.out.println(integerChange.integerReplacement2(7));
		System.out.println(integerChange.integerReplacement3(7));
	}

	//方式一：深度
	Map<Long, Integer> map = new HashMap<>();

	public int integerReplacement1(int n) {
		return dfs(n * 1L);
	}

	int dfs(long n) {
		if (n == 1) return 0;
		if (map.containsKey(n)) return map.get(n);
		int ans = n % 2 == 0 ? dfs(n / 2) : Math.min(dfs(n + 1), dfs(n - 1));
		map.put(n, ++ans);
		return ans;
	}

	//方式二：广度
	public int integerReplacement2(int n) {
		if (n == 1) return 0;
		Map<Long, Integer> map = new HashMap<>();
		Deque<Long> d = new ArrayDeque<>();
		d.addLast(n * 1L);
		map.put(n * 1L, 0);
		while (!d.isEmpty()) {
			long t = d.pollFirst();
			int step = map.get(t);
			long[] ns = t % 2 == 0 ? new long[]{t / 2} : new long[]{t + 1, t - 1};
			for (long x : ns) {
				if (x == 1) return step + 1;
				if (!map.containsKey(x)) {
					map.put(x, step + 1);
					d.addLast(x);
				}
			}
		}
		return -1;
	}

	//方式三：贪心
	public int integerReplacement3(int _n) {
		long n = _n;
		int ans = 0;
		while (n != 1) {
			if (n % 2 == 0) {
				n >>= 1;
			} else {
				if (n != 3 && ((n >> 1) & 1) == 1) {
					n++;
				} else {
					n--;
				}
			}
			ans++;
		}
		return ans;
	}
}
