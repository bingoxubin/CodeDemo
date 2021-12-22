package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/12/22 9:42 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DayOfYear {
    //Leetcode 1154. 一年中的第几天
    //示例：输入：date = "2019-01-09"   输出：9
    //分析：给你一个字符串date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
    //     通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
    //思路：
    public static void main(String[] args) {
        String date = "2019-01-09";
        DayOfYear dayOfYear = new DayOfYear();
        System.out.println(dayOfYear.dayOfYear(date));
    }

    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            months[1]++;
        }

        int res = 0;
        for (int i = 0; i < month - 1; i++) {
            res += months[i];
        }
        return res + day;
    }
}
