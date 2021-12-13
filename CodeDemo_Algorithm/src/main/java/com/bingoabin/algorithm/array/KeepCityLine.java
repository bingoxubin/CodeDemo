package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/12/13 9:39 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class KeepCityLine {
    //Leetcode 807. 保持城市天际线
    //示例：例子：
    //     输入： grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
    //     输出： 35
    //     解释：
    //     The grid is:
    //     [ [3, 0, 8, 4],
    //       [2, 4, 5, 7],
    //       [9, 2, 6, 3],
    //       [0, 3, 1, 0] ]
    //     从数组竖直方向（即顶部，底部）看“天际线”是：[9, 4, 8, 7]
    //     从水平水平方向（即左侧，右侧）看“天际线”是：[8, 7, 9, 3]
    //     在不影响天际线的情况下对建筑物进行增高后，新数组如下：
    //     gridNew = [ [8, 4, 8, 7],
    //                 [7, 4, 7, 7],
    //                 [9, 4, 8, 7],
    //                 [3, 3, 3, 3] ]
    //分析：在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。
    //     最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。
    //     建筑物高度可以增加的最大总和是多少？
    //思路：统计某行某列的最大值 然后进行统计
    public static void main(String[] args) {
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        KeepCityLine keepCityLine = new KeepCityLine();
        System.out.println(keepCityLine.maxIncreaseKeepingSkyline(grid));
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        //每行的最大值
        int[] rowsMax = new int[rows];
        //每列的最大值
        int[] colsMax = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rowsMax[i] = Math.max(rowsMax[i], grid[i][j]);
                colsMax[j] = Math.max(colsMax[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res += Math.min(rowsMax[i], colsMax[j]) - grid[i][j];
            }
        }
        return res;
    }
}
