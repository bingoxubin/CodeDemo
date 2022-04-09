package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/4/9 12:19 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RechingPoints {
    //Leetcode 780. 到达终点
    //示例：输入: sx = 1, sy = 1, tx = 5, ty = 3
    //     输出: true
    //     解释:
    //     可以通过以下一系列转换从起点转换到终点：
    //     (1, 1) -> (1, 2)
    //     (1, 2) -> (3, 2)
    //     (3, 2) -> (3, 5)
    //分析：给定四个整数sx,sy，tx和ty，如果通过一系列的转换可以从起点(sx, sy)到达终点(tx, ty)，则返回 true，否则返回false。
    //     从点(x, y)可以转换到(x, x+y) 或者(x+y, y)。
    //思路：
    public static void main(String[] args) {
        RechingPoints rechingPoints = new RechingPoints();
        System.out.println(rechingPoints.reachingPoints(1, 1, 3, 5));
        System.out.println(rechingPoints.reachingPoints(1, 1, 5, 3));
        System.out.println(rechingPoints.reachingPoints(1, 2, 5, 3));
        System.out.println(rechingPoints.reachingPoints(1, 2, 3, 5));
    }

    //辗转相除法
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) {
                ty %= tx;
            } else {
                tx %= ty;
            }
        }
        if (tx < sx || ty < sy) return false;
        System.out.println(sx + " " + sy + " " + tx + " " + ty);
        return sx == tx ? (ty - sy) % tx == 0 : (tx - sx) % ty == 0;
    }
}
