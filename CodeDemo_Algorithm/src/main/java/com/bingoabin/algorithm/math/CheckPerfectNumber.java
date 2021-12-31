package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/12/31 9:33 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CheckPerfectNumber {
    //Leetcode 507. 完美数
    //示例：输入：num = 28
    //     输出：true
    //     解释：28 = 1 + 2 + 4 + 7 + 14
    //          1, 2, 4, 7, 和 14 是 28 的所有正因子。
    //分析：对于一个正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
    //     给定一个整数n，如果是完美数，返回 true，否则返回 false
    //思路：
    public static void main(String[] args) {
        int num = 28;
        CheckPerfectNumber checkPerfectNumber = new CheckPerfectNumber();
        System.out.println(checkPerfectNumber.checkPerfectNumber(num));
    }

    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false;
        int count = 0;
        int sqrt = (int) Math.sqrt(num);
        //从2到num的平方根
        for (int i = 2; i <= sqrt; i++) {
            //如果能除尽，判断剩下的是不是自己本身，只能加一次，如果不是加上另一个
            if (num % i == 0) {
                if (i == num / i) {
                    count += i;
                } else {
                    count += i + num / i;
                }
            }
        }
        return (count + 1) == num;
    }
}
