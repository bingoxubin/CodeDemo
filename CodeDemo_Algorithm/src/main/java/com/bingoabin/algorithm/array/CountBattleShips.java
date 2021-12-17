package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/12/18 1:01 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountBattleShips {
    //Leetcode 419. 甲板上的战舰
    //示例：输入：board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]  输出：2
    //分析：给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
    //     战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。
    //     两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
    //思路：连在一起的算一个，有空格的算2个 计算个数
    public static void main(String[] args) {
        CountBattleShips countBattleShips = new CountBattleShips();
        char[][] board1 = {{'X', '.', '.', 'X'}, {'.', '.', '.', '.'}, {'.', '.', '.', 'X'}, {'.', '.', '.', '.'}};
        System.out.println(countBattleShips.countBattleships1(board1));
        char[][] board2 = {{'X', '.', '.', 'X'}, {'.', '.', '.', '.'}, {'.', '.', '.', 'X'}, {'.', '.', '.', '.'}};
        System.out.println(countBattleShips.countBattleships2(board2));
        char[][] board3 = {{'X', '.', '.', 'X'}, {'.', '.', '.', '.'}, {'.', '.', '.', 'X'}, {'.', '.', '.', '.'}};
        System.out.println(countBattleShips.countBattleships3(board3));
        char[][] board4 = {{'X', '.', '.', 'X'}, {'.', '.', '.', '.'}, {'.', '.', '.', 'X'}, {'.', '.', '.', '.'}};
        System.out.println(countBattleShips.countBattleships4(board4));
    }

    //遍历扫描
    public int countBattleships1(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    for (int k = j + 1; k < col && board[i][k] == 'X'; ++k) {
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < row && board[k][j] == 'X'; ++k) {
                        board[k][j] = '.';
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    //枚举起点
    public int countBattleships2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    //自己实现一遍
    public int countBattleships3(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //如果当前位置是'X',那么分别向左 向上找一下，如果是'X'，那么就进行跳过，结果就不用+1
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    //自己实现一遍
    public int countBattleships4(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //如果当前位置是'X',后面或者下面还是'X'的话，都设置为'.'（注意是连续的），如果断了的话，就继续+1
                if (board[i][j] == 'X') {
                    for (int k = i + 1; k < row && board[k][j] == 'X'; k++) {
                        board[k][j] = '.';
                    }
                    for (int k = j + 1; k < col && board[i][k] == 'X'; k++) {
                        board[i][k] = '.';
                    }
                    res++;
                }
            }
        }
        return res;
    }
}
