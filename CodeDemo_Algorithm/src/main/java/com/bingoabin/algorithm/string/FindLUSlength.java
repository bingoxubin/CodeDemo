package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2022/3/6 12:50 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindLUSlength {
    //Leetcode 521. 最长特殊序列 Ⅰ
    //示例：给你两个字符串a和b，请返回 这两个字符串中 最长的特殊序列 的长度。如果不存在，则返回 -1。
    //     「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
    //     字符串s的子序列是在从s中删除任意数量的字符后可以获得的字符串。字符串s的子序列是在从s中删除任意数量的字符后可以获得的字符串。
    //     字符串s的子序列是在从s中删除任意数量的字符后可以获得的字符串。例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。 "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
    //分析：输入: a = "aba", b = "cdc"
    //     字符串s的子序列是在从s中删除任意数量的字符后可以获得的字符串。输出: 3
    //     字符串s的子序列是在从s中删除任意数量的字符后可以获得的字符串。解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
    //思路：
    public static void main(String[] args) {
        String a = "aba";
        String b = "cdc";
    }

    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}
