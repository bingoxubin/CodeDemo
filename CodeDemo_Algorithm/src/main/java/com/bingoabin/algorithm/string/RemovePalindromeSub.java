package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2022/1/23 1:31 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RemovePalindromeSub {
    //Leetcode 1332. 删除回文子序列
    //示例：输入：s = "abb"
    //     输出：2
    //     解释："abb" -> "bb" -> "".
    //     先删除回文子序列 "a"，然后再删除 "bb"。
    //分析：给你一个字符串s，它仅由字母'a' 和 'b'组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
    //     返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
    //     「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
    //     「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
    //思路：
    public static void main(String[] args) {
        String s = "abb";
        RemovePalindromeSub removePalindromeSub = new RemovePalindromeSub();
        System.out.println(removePalindromeSub.removePalindromeSub(s));
    }

    public int removePalindromeSub(String s) {
        char[] arr = s.toCharArray();
        if (arr.length == 0) {
            return 0;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            if (arr[start++] != arr[end--]) {
                return 2;
            }
        }
        return 1;
    }
}
