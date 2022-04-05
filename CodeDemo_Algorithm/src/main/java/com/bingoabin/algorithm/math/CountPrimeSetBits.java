package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/4/5 2:19 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountPrimeSetBits {
    //Leetcode 762. 二进制表示中质数个计算置位
    //示例：输入：left = 6, right = 10
    //     输出：4
    //     解释：
    //     6 -> 110 (2 个计算置位，2 是质数)
    //     7 -> 111 (3 个计算置位，3 是质数)
    //     9 -> 1001 (2 个计算置位，2 是质数)
    //     10-> 1010 (2 个计算置位，2 是质数)
    //共计 4 个计算置位为质数的数字。
    //分析：给你两个整数left和right ，在闭区间 [left, right]范围内，统计并返回 计算置位位数为质数 的整数个数。
    //     计算置位位数 就是二进制表示中 1 的个数。
    //     例如， 21的二进制表示10101有 3 个计算置位。
    //思路：
    public static void main(String[] args) {
        CountPrimeSetBits countPrimeSetBits = new CountPrimeSetBits();
        System.out.println(countPrimeSetBits.countDigit(4));
        System.out.println(countPrimeSetBits.countPrimeSetBits(6, 10));

        System.out.println(countPrimeSetBits.countPrimeSetBits1(6, 10));
    }

    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int temp = countDigit(i);
            if (temp == 2 || temp == 3 || temp == 5 || temp == 7 || temp == 11 || temp == 13 || temp == 17 || temp == 19) {
                count++;
            }
        }
        return count;
    }

    public int countDigit(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>>= 1;
        }
        return count;
    }

    //质数的实现方式
    public int countPrimeSetBits1(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (isNum(Integer.bitCount(i))) {
                count++;
            }
        }
        return count;
    }

    //是否是质数
    public boolean isNum(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if ((num % i) == 0) {
                return false;
            }
        }
        return true;
    }


}
