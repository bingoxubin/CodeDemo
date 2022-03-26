package com.bingoabin.algorithm.graph;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/3/24 9:44 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ImageSmoother {
    //Leetcode 661. 图片平滑器
    //示例：输入:img = [[1,1,1],[1,0,1],[1,1,1]]
    //     输出:[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
    //     解释:
    //     对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
    //     对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
    //     对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
    //分析：图像平滑器 是大小为3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
    //     每个单元格的 平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
    //     如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
    //     给你一个表示图像灰度的 m x n 整数矩阵 img ，返回对图像的每个单元格平滑处理后的图像 。
    //思路：
    public static void main(String[] args) {
        int[][] M = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        ImageSmoother imageSmoother = new ImageSmoother();
        System.out.println(Arrays.deepToString(imageSmoother.imageSmoother(M)));
    }

    //普通迭代的方式
    public int[][] imageSmoother(int[][] M) {
        int row = M.length;
        int col = M[0].length;

        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = 0;
                for (int nrow = i - 1; nrow <= i + 1; nrow++) {
                    for (int ncol = j - 1; ncol <= j + 1; ncol++) {
                        //加一个溢出值的判断
                        if (nrow >= 0 && nrow < row && ncol >= 0 && ncol < col) {
                            res[i][j] += M[nrow][ncol];
                            count++;
                        }
                    }
                }
                res[i][j] /= count;
            }
        }
        return res;
    }
}
