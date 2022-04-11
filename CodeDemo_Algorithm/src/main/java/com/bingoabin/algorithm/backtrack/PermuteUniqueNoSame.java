package com.bingoabin.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/5/24 7:29 下午
 */
public class PermuteUniqueNoSame {
	//NC43 没有重复项数字的所有排列  https://www.nowcoder.com/practice/4bcf3081067a4d028f95acee3ddcd2b1?tpId=117&tqId=37740&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：输入：[1,2,3]  输出:[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
	//分析：给出一个各不相同的数组，里面元素各不相同，求这个数组的所有排列方式
	//思路：采用回溯的方式求出结果
	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		System.out.println(permute(arr));
	}

	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		Deque<Integer> path = new LinkedList<>();
		boolean[] used = new boolean[num.length];
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

	public static void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used, ArrayList<
			ArrayList<Integer>> res) {
		if (depth == nums.length) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (used[i]) {
				continue;
			}
			path.addLast(nums[i]);
			used[i] = true;
			dfs(nums, depth + 1, path, used, res);
			path.removeLast();
			used[i] = false;
		}
	}
}
