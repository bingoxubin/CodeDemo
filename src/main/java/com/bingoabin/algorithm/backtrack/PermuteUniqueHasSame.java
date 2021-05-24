package com.bingoabin.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/5/24 7:29 下午
 */
public class PermuteUniqueHasSame {
	//NC42 有重复项数字的所有排列 https://www.nowcoder.com/practice/a43a2b986ef34843ac4fdd9159b69863?tpId=117&tqId=37739&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：输入：[1,1,2] 输出:[[1,1,2],[1,2,1],[2,1,1]]
	//分析：给一个数组，其中数组中的元素有相同的项，求出该数组所有不相同的排列
	//思路：用回溯方式，排除重复选项
	public static void main(String[] args) {
		int[] arr = {1, 1, 2};
		System.out.println(permuteUnique(arr));
	}

	public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		Deque<Integer> path = new LinkedList<>();
		boolean[] used = new boolean[num.length];
		Arrays.sort(num);
		dfs(num, 0, path, used, res);
		//无用的定制排序
		// Collections.sort(res, new Comparator<ArrayList<Integer>>() {
		// 	@Override
		// 	public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
		// 		int value = 0;
		// 		for (int i = 0; i < o1.size(); i++) {
		// 			if (o1.get(i) > o2.get(i)) {
		// 				value = -1;
		// 				break;
		// 			}else if(o1.get(i) < o2.get(i)){
		// 				value = 1;
		// 				break;
		// 			}else{
		// 				continue;
		// 			}
		// 		}
		// 		return value;
		// 	}
		// });
		return res;
	}

	public static void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used, ArrayList<ArrayList<Integer>> res) {
		if (depth == nums.length) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
				continue;
			}
			if (used[i]) continue;
			path.addLast(nums[i]);
			used[i] = true;
			dfs(nums, depth + 1, path, used, res);
			path.removeLast();
			used[i] = false;
		}
	}
}
