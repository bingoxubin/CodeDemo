package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/4/11 9:11 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountNumbersWithUniqueDigits {
    //Leetcode 357. 统计各位数字都不同的数字个数
    //示例：输入：n = 2
    //     输出：91
    //     解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
    //分析：给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
    //思路：
    public static void main(String[] args) {
        CountNumbersWithUniqueDigits countNumbersWithUniqueDigits = new CountNumbersWithUniqueDigits();
        System.out.println(countNumbersWithUniqueDigits.countNumbersWithUniqueDigits(2));
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 1) return 1;
        int ans = 10;
        for (int i = 2, last = 9; i <= n; i++) {
            int cur = last * (10 - i + 1);
            ans += cur;
            last = cur;
        }
        return ans;
    }
}
