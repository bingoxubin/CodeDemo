package com.bingoabin.algorithm.slipwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/12/16 9:54 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class VisiblePoints {
    //Leetcode 1610. 可见点的最大数目
    //示例：输入：points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]   输出：3
    //     解释：阴影区域代表你的视野。在你的视野中，所有的点都清晰可见，尽管 [2,2] 和 [3,3]在同一条直线上，你仍然可以看到 [3,3] 。
    //分析：给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
    //     最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。
    //     你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
    //     对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
    //     同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
    //     返回你能看到的点的最大数目。
    //思路：滑动窗口的方式
    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>(Arrays.asList(2, 1)));
        points.add(new ArrayList<>(Arrays.asList(2, 2)));
        points.add(new ArrayList<>(Arrays.asList(3, 3)));
        List<Integer> location = new ArrayList<>(Arrays.asList(1, 1));
        VisiblePoints vp = new VisiblePoints();
        System.out.println(vp.visiblePoints(points, 90, location));
    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int sameCnt = 0;
        List<Double> polarDegrees = new ArrayList<>();
        int locationX = location.get(0);
        int locationY = location.get(1);
        for (List<Integer> point : points) {
            int x = point.get(0);
            int y = point.get(1);
            if (x == locationX && y == locationY) {
                sameCnt++;
                continue;
            }
            Double degree = Math.atan2(y - locationY, x - locationX);
            polarDegrees.add(degree);
        }
        Collections.sort(polarDegrees);

        int m = polarDegrees.size();
        for (int i = 0; i < m; ++i) {
            polarDegrees.add(polarDegrees.get(i) + 2 * Math.PI);
        }

        int maxCnt = 0;
        int right = 0;
        double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; ++i) {
            Double curr = polarDegrees.get(i) + toDegree;
            while (right < polarDegrees.size() && polarDegrees.get(right) <= curr) {
                right++;
            }
            maxCnt = Math.max(maxCnt, right - i);
        }
        return maxCnt + sameCnt;
    }
}
