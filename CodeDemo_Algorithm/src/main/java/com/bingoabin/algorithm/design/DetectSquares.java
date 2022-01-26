package com.bingoabin.algorithm.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xubin34
 * @Date: 2022/1/26 9:59 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DetectSquares {
    //Leetcode 2013. 检测正方形
    //示例：输入：["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
    //     [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
    //     输出：
    //     [null, null, null, null, 1, 0, null, 2]
    //     解释：
    //     DetectSquares detectSquares = new DetectSquares();
    //     detectSquares.add([3, 10]);
    //     detectSquares.add([11, 2]);
    //     detectSquares.add([3, 2]);
    //     detectSquares.count([11, 10]); // 返回 1 。你可以选择：
    //                                    //   - 第一个，第二个，和第三个点
    //     detectSquares.count([14, 8]);  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
    //     detectSquares.add([11, 2]);    // 允许添加重复的点。
    //     detectSquares.count([11, 10]); // 返回 2 。你可以选择：
    //                                    //   - 第一个，第二个，和第三个点
    //                                    //   - 第一个，第三个，和第四个点
    //分析：给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：
    //     添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
    //     给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
    //     轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。
    //     实现 DetectSquares 类：
    //     DetectSquares() 使用空数据结构初始化对象
    //     void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
    //     int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
    //思路：
    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
        System.out.println(detectSquares.count(new int[]{14, 8}));
        detectSquares.add(new int[]{11, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
    }

    //key为x轴的值，value的hashmap为key是y的值，value是这个点的个数
    Map<Integer, Map<Integer, Integer>> row2Col;

    public DetectSquares() {
        row2Col = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
        col2Cnt.put(y, col2Cnt.getOrDefault(y, 0) + 1);
        row2Col.put(x, col2Cnt);
    }

    public int count(int[] point) {
        int x = point[0], y = point[1];
        int ans = 0;
        Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
        for (int ny : col2Cnt.keySet()) {
            if (ny == y) continue;
            int c1 = col2Cnt.get(ny);
            int len = y - ny;
            int[] nums = new int[]{x + len, x - len};
            for (int nx : nums) {
                Map<Integer, Integer> temp = row2Col.getOrDefault(nx, new HashMap<>());
                int c2 = temp.getOrDefault(y, 0), c3 = temp.getOrDefault(ny, 0);
                ans += c1 * c2 * c3;
            }
        }
        return ans;
    }
}
