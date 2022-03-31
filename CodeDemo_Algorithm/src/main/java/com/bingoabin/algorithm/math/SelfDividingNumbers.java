package com.bingoabin.algorithm.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/31 10:04 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SelfDividingNumbers {
    //Leetcode 728. 自除数
    //示例:输入：left = 1, right = 22
    //    输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
    //分析：自除数是指可以被它包含的每一位数整除的数。
    //    例如，128 是一个 自除数 ，因为128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
    //    自除数 不允许包含 0 。
    //    给定两个整数left和right ，返回一个列表，列表的元素是范围[left, right]内所有的 自除数 。
    //思路：
    public static void main(String[] args) {
        SelfDividingNumbers selfDividingNumbers = new SelfDividingNumbers();
        System.out.println(selfDividingNumbers.selfDividingNumbers(1, 22));
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isNum(i)) res.add(i);
        }
        return res;
    }

    public boolean isNum(int num) {
        int value = num;
        while (num > 0) {
            int bit = num % 10;
            if (bit == 0 || value % bit != 0) return false;
            num = num / 10;
        }
        return true;
    }

    //自己实现一遍
    public boolean isNum1(int num) {
        int value = num;
        while (num > 0) {
            int bit = num % 10;
            if (bit == 0 || value % bit != 0) return false;
            num /= 10;
        }
        return true;
    }
}
