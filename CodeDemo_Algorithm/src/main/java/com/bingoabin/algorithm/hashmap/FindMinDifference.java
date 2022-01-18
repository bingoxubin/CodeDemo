package com.bingoabin.algorithm.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/1/18 10:15 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindMinDifference {
    //Leetcode 539. 最小时间差
    //示例：输入：timePoints = ["23:59","00:00"]  输出：1
    //分析：给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
    //思路：
    public static void main(String[] args) {
        List<String> timePoints = new ArrayList<>(Arrays.asList("23:59", "00:00"));
        FindMinDifference findMinDifference = new FindMinDifference();
        System.out.println(findMinDifference.findMinDifference1(timePoints));
        System.out.println(findMinDifference.findMinDifference2(timePoints));
    }

    //排序
    public int findMinDifference1(List<String> timePoints) {
        int n = timePoints.size() * 2;
        int[] nums = new int[n];
        for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {
            String[] ss = timePoints.get(i).split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            nums[idx] = h * 60 + m;
            nums[idx + 1] = nums[idx] + 1440;
        }
        Arrays.sort(nums);
        int ans = nums[1] - nums[0];
        for (int i = 0; i < n - 1; i++) ans = Math.min(ans, nums[i + 1] - nums[i]);
        return ans;
    }

    //模拟 hash计数
    public int findMinDifference2(List<String> timePoints) {
        int n = timePoints.size();
        if (n >= 1440) return 0;
        int[] cnts = new int[1440 * 2 + 10];
        for (String s : timePoints) {
            String[] ss = s.split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            cnts[h * 60 + m]++;
            cnts[h * 60 + m + 1440]++;
        }
        int ans = 1440, last = -1;
        for (int i = 0; i <= 1440 * 2 && ans != 0; i++) {
            if (cnts[i] == 0) continue;
            if (cnts[i] > 1) ans = 0;
            else if (last != -1) ans = Math.min(ans, i - last);
            last = i;
        }
        return ans;
    }
}
