package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/12/9 9:59 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ValidTicTacToe {
	//Leetcode 794. 有效的井字游戏
	//示例：输入：board = ["XOX","O O","XOX"]   输出：true
	//     输入：board = ["XXX","   ","OOO"]   输出：false
	//分析：给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
	//     井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
	//     以下是井字游戏的规则：
	//        玩家轮流将字符放入空位（' '）中。
	//        玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
	//        'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
	//        当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
	//        当所有位置非空时，也算为游戏结束。
	//        如果游戏结束，玩家不允许再放置字符。
	//思路：分情况讨论，给定的棋盘大小固定，对于无效情况进行分情况讨论即可：
	//     由于 X 先手，O 后手，两者轮流下子。因此 O 的数量不会超过 X，且两者数量差不会超过 11，否则为无效局面；
	//     若局面是 X 获胜，导致该局面的最后一个子必然是 X，此时必然有 X 数量大于 O（X 为先手），否则为无效局面；
	//     若局面是 O 获胜，导致该局面的最后一个子必然是 O，此时必然有 X 数量等于 O（X 为先手），否则为无效局面；
	//     局面中不可能出现两者同时赢（其中一方赢后，游戏结束）。
	public static void main(String[] args) {
		String[] board = {"XOX", "O O", "XOX"};
		ValidTicTacToe validTicTacToe = new ValidTicTacToe();
		System.out.println(validTicTacToe.validTicTacToe(board));

		String[] board1 = {"XXX", "   ", "OOO"};
		ValidTicTacToe validTicTacToe1 = new ValidTicTacToe();
		System.out.println(validTicTacToe1.validTicTacToe(board1));
	}

	public boolean validTicTacToe(String[] board) {
		char[][] cs = new char[3][3];
		int x = 0, o = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char c = board[i].charAt(j);
				if (c == 'X') x++;
				else if (c == 'O') o++;
				cs[i][j] = c;
			}
		}
		boolean a = check(cs, 'X'), b = check(cs, 'O');
		if (o > x || x - o > 1) return false;
		if (a && x <= o) return false;
		if (b && o != x) return false;
		if (a && b) return false;
		return true;
	}

	boolean check(char[][] cs, char c) {
		for (int i = 0; i < 3; i++) {
			if (cs[i][0] == c && cs[i][1] == c && cs[i][2] == c) return true;
			if (cs[0][i] == c && cs[1][i] == c && cs[2][i] == c) return true;
		}
		boolean a = true, b = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) a &= cs[i][j] == c;
				if (i + j == 2) b &= cs[i][j] == c;
			}
		}
		return a || b;
	}
}
