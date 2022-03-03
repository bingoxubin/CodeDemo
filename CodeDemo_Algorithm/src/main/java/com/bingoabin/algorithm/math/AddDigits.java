package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/3/3 10:00 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class AddDigits {
    //Leetcode 258. 各位相加
    //示例：输入: num = 38
    //     输出: 2
    //     解释: 各位相加的过程为：
    //     38 --> 3 + 8 --> 11
    //     11 --> 1 + 1 --> 2
    //     由于2 是一位数，所以返回 2。
    //分析：给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
    //思路：
    public static void main(String[] args) {
        AddDigits addDigits = new AddDigits();
        System.out.println(addDigits.addDigits(38));
    }

    public int addDigits(int num) {
        while (num > 9) {
            num = getNum(num);
        }
        return num;
    }

    public int getNum(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}
