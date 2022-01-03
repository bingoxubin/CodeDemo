package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/1/3 10:36 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DayOfTheWeek {
    //Leetcode 1185. 一周中的第几天
    //示例：输入：day = 31, month = 8, year = 2019
    //    输出："Saturday"
    //分析：给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
    //     输入为三个整数：day、month 和year，分别表示日、月、年。
    //     您返回的结果必须是这几个值中的一个{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
    //思路：
    public static void main(String[] args) {
        DayOfTheWeek dayOfTheWeek = new DayOfTheWeek();
        System.out.println(dayOfTheWeek.dayOfTheWeek(31, 8, 2019));
        System.out.println(dayOfTheWeek.dayOfTheWeek1(31, 8, 2019));
    }

    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int sum = 4;
        for (int i = 1971; i < year; i++) {
            sum += 365;
            if (((i % 4 == 0) && (i % 100 != 0)) || i % 400 == 0) sum++;
        }
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 0; i < month - 1; i++) sum += months[i];
        if (month >= 3 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) sum++;
        sum += day;
        return week[sum % 7];
    }

    //自己实现一遍
    public String dayOfTheWeek1(int day, int month, int year) {
        //结果中的星期
        String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        //1971年1月1日  周五
        int res = 4;  //1号周五，最后res + 1 就是Friday
        //先计算年的天数
        for (int i = 1971; i < year; i++) {
            res += 365;
            //闰年+1天
            if ((i % 4 == 0) && (i % 100 != 0 || i % 400 == 0)) {
                res++;
            }
        }
        //计算当月的月数折算成天数
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 0; i < month - 1; i++) {
            res += months[i];
        }
        //如果是润年 + 1天
        if (month >= 3 && (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)) {
            res++;
        }
        //计算day 折算成天数
        res += day;
        return weeks[res % 7]; //res天数 除以7的余数，就是星期几
    }
}
