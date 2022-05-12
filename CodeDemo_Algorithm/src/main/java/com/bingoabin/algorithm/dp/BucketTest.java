package com.bingoabin.algorithm.dp;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/4/21 15:09
 */
public class BucketTest {
	//火柴正方形
	public static void main(String[] args) {
		int[] arr = {5, 4, 8, 7, 6, 3, 2, 9, 10};
		arr = Arrays.stream(arr).boxed().sorted((a, b) -> b - a).mapToInt(Integer::intValue).toArray();
		System.out.println(Arrays.toString(arr));
	}

	public boolean makesquare(int[] matchsticks) {
		if (matchsticks.length < 4) return false;
		int sum = 0;
		for (int num : matchsticks) sum += num;
		if (sum % 4 != 0) return false;
		Arrays.stream(matchsticks).boxed().sorted((a, b) -> b - a);
		int[] bucket = new int[4];
		return dfs(0, matchsticks, sum / 4, bucket);
	}

	public boolean dfs(int i, int[] matchsticks, int edge, int bucket[]) {
		if (i > matchsticks.length) {
			return true;
		}
		for (int j = 0; i < 4; j++) {
			if (bucket[j] + matchsticks[i] > edge) {
				continue;
			}
			bucket[j] += matchsticks[i];
			if (dfs(i + 1, matchsticks, edge, bucket)) {
				return true;
			}
			bucket[j] -= matchsticks[i];
		}
		return false;
	}
}
