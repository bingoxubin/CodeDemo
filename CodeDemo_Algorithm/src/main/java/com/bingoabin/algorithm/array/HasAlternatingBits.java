package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2022/3/28 10:55 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class HasAlternatingBits {
    //Leetcode 693. 交替位二进制数
    //示例：输入：n = 5
    //     输出：true
    //     解释：5 的二进制表示是：101
    //分析：给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
    //思路：
    public static void main(String[] args) {
        HasAlternatingBits hasAlternatingBits = new HasAlternatingBits();
        System.out.println(hasAlternatingBits.hasAlternatingBits(5));
    }

    public boolean hasAlternatingBits(int n) {
        int pre = n & 1;
        n >>>= 1;
        while (n != 0) {
            if ((n & 1) == pre) return false;
            pre = n & 1;
            n >>>= 1;
        }
        return true;
    }
}
