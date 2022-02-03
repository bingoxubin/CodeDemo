package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/2/4 12:10 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountGoodRectangles {
    //Leetcode 1725. 可以形成最大正方形的矩形数目
    //示例：示例 1：
    //     输入：rectangles = [[5,8],[3,9],[5,12],[16,5]]
    //     输出：3
    //     解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
    //     最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
    //分析：给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
    //     如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
    //     设 maxLen 为可以从矩形数组rectangles 切分得到的 最大正方形 的边长。
    //     请你统计有多少个矩形能够切出边长为 maxLen 的正方形，并返回矩形 数目 。
    //思路：
    public static void main(String[] args) {
        int[][] rectangles = {{5, 8}, {3, 9}, {5, 12}, {16, 5}};
        CountGoodRectangles countGoodRectangles = new CountGoodRectangles();
        System.out.println(countGoodRectangles.countGoodRectangles(rectangles));
    }

    public int countGoodRectangles(int[][] rectangles) {
        //统计正方形的边长数组
        int[] res = new int[rectangles.length];
        //index表示res统计到的哪一个
        int index = 0;
        //max表示记录中间的最大值
        int max = 0;
        for (int[] rec : rectangles) {
            res[index++] = Math.min(rec[0], rec[1]);
            if (res[index - 1] > max) {
                max = res[index - 1];
            }
        }
        int count = 0;
        for (int i : res) {
            if (max == i) {
                count++;
            }
        }
        return count;
    }
}
