package com.bingoabin.algorithm.array;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/1/1 11:38 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Construct2DArray {
    //Leetcode 2022. 将一维数组转变成二维数组
    //示例：输入：original = [1,2,3,4], m = 2, n = 2
    //     输出：[[1,2],[3,4]]
    //     解释：
    //     构造出的二维数组应该包含 2 行 2 列。
    //     original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
    //     original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
    //分析：给你一个下标从 0开始的一维整数数组original和两个整数m和n。你需要使用original中所有元素创建一个m行n列的二维数组。
    //     original中下标从 0到 n - 1（都 包含 ）的元素构成二维数组的第一行，下标从 n到 2 * n - 1（都 包含）的元素构成二维数组的第二行，依此类推。
    //     请你根据上述过程返回一个m x n的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。
    //思路：
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4};
        Construct2DArray construct2DArray = new Construct2DArray();
        System.out.println(Arrays.deepToString(construct2DArray.construct2DArray(original, 2, 2)));
    }

    public int[][] construct2DArray(int[] original, int m, int n) {
        //如果长度不一致，直接返回空
        if (original.length != m * n) {
            return new int[][]{};
        }
        //建一个新的二维数组
        int[][] res = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //从index的0开始，不但往后移，补充到res二维数组上去
                res[i][j] = original[index++];
            }
        }
        return res;
    }
}
