package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: xubin34
 * @Date: 2021/9/18 10:20 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class IsValidSudoKu {
	//Leetcode 36. 有效的数独
	//示例：输入：board =
	//          [['5','3','.','.','7','.','.','.','.']
	//          ,['6','.','.','1','9','5','.','.','.']
	//          ,['.','9','8','.','.','.','.','6','.']
	//          ,['8','.','.','.','6','.','.','.','3']
	//          ,['4','.','.','8','.','3','.','.','1']
	//          ,['7','.','.','.','2','.','.','.','6']
	//          ,['.','6','.','.','.','.','2','8','.']
	//          ,['.','.','.','4','1','9','.','.','5']
	//          ,['.','.','.','.','8','.','.','7','9']]
	//          输出：true
	//分析：给一个数独 判断是否有效
	//思路：方式一：哈希表
	//     方式二：数组
	//     方式三：位运算
	public static void main(String[] args) {
		char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
		                  {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
		                  {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
		                  {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
		                  {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
		                  {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
		                  {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
		                  {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
		                  {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		IsValidSudoKu isValidSudoKu = new IsValidSudoKu();
		System.out.println(isValidSudoKu.isValidSudoku1(board));
		System.out.println(isValidSudoKu.isValidSudoku2(board));
		System.out.println(isValidSudoKu.isValidSudoku3(board));
	}

	//方式一：hashmap
	public boolean isValidSudoku1(char[][] board) {
		Map<Integer, Set<Integer>> row = new HashMap<>(), col = new HashMap<>(), area = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			row.put(i, new HashSet<>());
			col.put(i, new HashSet<>());
			area.put(i, new HashSet<>());
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if (c == '.') continue;
				int u = c - '0';
				int idx = i / 3 * 3 + j / 3;
				if (row.get(i).contains(u) || col.get(j).contains(u) || area.get(idx).contains(u)) return false;
				row.get(i).add(u);
				col.get(j).add(u);
				area.get(idx).add(u);
			}
		}
		return true;
	}

	//方式二：数组
	public boolean isValidSudoku2(char[][] board) {
		Map<Integer, Set<Integer>> row = new HashMap<>(), col = new HashMap<>(), area = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			row.put(i, new HashSet<>());
			col.put(i, new HashSet<>());
			area.put(i, new HashSet<>());
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if (c == '.') continue;
				int u = c - '0';
				int idx = i / 3 * 3 + j / 3;
				if (row.get(i).contains(u) || col.get(j).contains(u) || area.get(idx).contains(u)) return false;
				row.get(i).add(u);
				col.get(j).add(u);
				area.get(idx).add(u);
			}
		}
		return true;
	}

	//方式三：位运算
	public boolean isValidSudoku3(char[][] board) {
		int[] row = new int[10], col = new int[10], area = new int[10];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if (c == '.') continue;
				int u = c - '0';
				int idx = i / 3 * 3 + j / 3;
				if ((((row[i] >> u) & 1) == 1) || (((col[j] >> u) & 1) == 1) || (((area[idx] >> u) & 1) == 1))
					return false;
				row[i] |= (1 << u);
				col[j] |= (1 << u);
				area[idx] |= (1 << u);
			}
		}
		return true;
	}
}
