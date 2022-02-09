package com.bingoabin.algorithm.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/2/4 12:22 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindMinFibonacciNumbers {
    //Leetcode 1414. 和为 K 的最少斐波那契数字数目
    //示例：输入：k = 7
    //     输出：2
    //     解释：斐波那契数字为：1，1，2，3，5，8，13，……
    //     对于 k = 7 ，我们可以得到 2 + 5 = 7 。
    //分析：给你数字 k，请你返回和为k的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
    //     斐波那契数字定义为：
    //     F1 = 1
    //     F2 = 1
    //     Fn = Fn-1 + Fn-2， 其中 n > 2 。
    //     数据保证对于给定的 k，一定能找到可行解。
    //思路：
    public static void main(String[] args) {
        FindMinFibonacciNumbers findMinFibonacciNumbers = new FindMinFibonacciNumbers();
        System.out.println(findMinFibonacciNumbers.findMinFibonacciNumbers(7));
    }

    public int findMinFibonacciNumbers(int k) {
        List<Integer> f = new ArrayList<Integer>();
        f.add(1);
        int a = 1, b = 1;
        while (a + b <= k) {
            int c = a + b;
            f.add(c);
            a = b;
            b = c;
        }
        int ans = 0;
        for (int i = f.size() - 1; i >= 0 && k > 0; i--) {
            int num = f.get(i);
            if (k >= num) {
                k -= num;
                ans++;
            }
        }
        return ans;
    }
}
