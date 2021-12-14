package com.bingoabin.algorithm.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author: xubin34
 * @Date: 2021/12/14 10:07 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ScheduleCourse {
    //Leetcode 630. 课程表 III
    //示例：输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]  输出：3
    //     解释：
    //     这里一共有 4 门课程，但是你最多可以修 3 门：
    //     首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
    //     第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
    //     第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
    //     第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
    //分析：这里有 n 门不同的在线课程，按从 1 到 n编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会持续上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
    //     你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
    //     返回你最多可以修读的课程数目。
    //思路：贪心算法
    public static void main(String[] args) {
        //【持续上课的天数，截止何时完成】
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        ScheduleCourse scheduleCourse = new ScheduleCourse();
        System.out.println(scheduleCourse.scheduleCourse(courses));
    }

    public int scheduleCourse(int[][] courses) {
        //将二维数组按照上课截止完成时间排序
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        //创建一个大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for (int[] c : courses) {
            //持续时间；最晚结束时间
            int d = c[0], e = c[1];
            sum += d;
            q.add(d);
            if (sum > e) sum -= q.poll();
        }
        return q.size();
    }
}
