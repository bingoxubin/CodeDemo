package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/2/20 9:44 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class IsOneBitCharacter {
    //Leetcode 717. 1比特与2比特字符
    //示例：输入: bits = [1, 0, 0]
    //     输出: true
    //     解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
    //     所以最后一个字符是一比特字符。
    //分析：有两种特殊字符：
    //     第一种字符可以用一个比特0来表示
    //     第二种字符可以用两个比特(10或11)来表示、
    //     给定一个以 0 结尾的二进制数组bits，如果最后一个字符必须是一位字符，则返回 true 。
    //思路：
    public static void main(String[] args) {
        int[] bits = {1, 0, 0};
        IsOneBitCharacter isOneBitCharacter = new IsOneBitCharacter();
        System.out.println(isOneBitCharacter.isOneBitCharacter(bits));
    }

    //从前往后扫描每个「比特字」，如果最后一个「比特字」的开头为 n - 1n−1 返回 True，否则返回 False。
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, idx = 0;
        while (idx < n - 1) {
            if (bits[idx] == 0) {
                idx++;
            } else {
                idx += 2;
            }
        }
        return idx == n - 1;
    }
}
