package com.bingoabin.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/2/15 9:57 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LuckyNumbers {
    //Leetcode 1380. 矩阵中的幸运数
    //示例：输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
    //     输出：[15]
    //     解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
    //分析：给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
    //     幸运数是指矩阵中满足同时下列两个条件的元素：
    //        在同一行的所有元素中最小
    //        在同一列的所有元素中最大
    //思路：
    public static void main(String[] args) {
        int[][] matrix = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        LuckyNumbers luckyNumbers = new LuckyNumbers();
        System.out.println(luckyNumbers.luckyNumbers(matrix));
        System.out.println(luckyNumbers.luckyNumbers1(matrix));
    }

    //逐个求解
    public List<Integer> luckyNumbers1(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        //从第一行开始
        for (int i = matrix.length - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            //找到这一行的最小值
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    index = j;
                }
            }
            boolean flag = true;
            //然后看是不是这一列的最大值
            for (int[] ints : matrix) {
                if (min < ints[index]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(min);
            }
        }
        return result;
    }

    //进行预求解，先预求出两个数组，每行的最小值，每列的最大值
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] minRow = new int[m];
        Arrays.fill(minRow, Integer.MAX_VALUE);
        int[] maxCol = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minRow[i] = Math.min(minRow[i], matrix[i][j]);
                maxCol[j] = Math.max(maxCol[j], matrix[i][j]);
            }
        }
        List<Integer> ret = new ArrayList<>();
        //遍历二维矩阵中的值，如果当前值等于每行的最小值，每列的最大值那么加入到结果集中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }
}
