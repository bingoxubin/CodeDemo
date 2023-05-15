package com.bingoabin.acwing._01basic;

import java.util.*;

/**
 * @author bingoabin
 * @date 2023/5/15 10:34
 * @Description:
 */
public class _20SectionMerge {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] res = new int[n][2];
		for (int i = 0; i < n; i++) {
			res[i][0] = sc.nextInt();
			res[i][1] = sc.nextInt();
		}

		System.out.println(getUnionNum(res));
	}

	public static int getUnionNum(int[][] arr) {
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		List<int[]> res = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			int[] nums = arr[i];
			if (res.isEmpty() || res.get(res.size() - 1)[1] < nums[0]) {
				res.add(nums);
			} else {
				int maxRight = Math.max(nums[1], res.get(res.size() - 1)[1]);
				res.get(res.size() - 1)[1] = maxRight;
			}
		}
		return res.size();
	}
}
