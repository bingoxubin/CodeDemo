package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2022/2/9 9:09 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LongestNiceSubstring {
    //Leetcode 1763. 最长的美好子字符串
    //示例：输入：s = "YazaAay"
    //    输出："aAa"
    //    解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
    //    "aAa" 是最长的美好子字符串
    //分析：当一个字符串 s包含的每一种字母的大写和小写形式 同时出现在 s中，就称这个字符串s是 美好 字符串。比方说，"abABB"是美好字符串，因为'A' 和'a'同时出现了，且'B' 和'b'也同时出现了。
    //     然而，"abA"不是美好字符串因为'b'出现了，而'B'没有出现。
    //     给你一个字符串s，请你返回s最长的美好子字符串。如果有多个答案，请你返回最早出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
    //思路：
    public static void main(String[] args) {
        String s = "YazaAay";
        LongestNiceSubstring longestNiceSubstring = new LongestNiceSubstring();
        System.out.println(longestNiceSubstring.longestNiceSubstring(s));
    }

    public String longestNiceSubstring(String s) {
        int n = s.length();
        int idx = -1, len = 0;
        for (int i = 0; i < n; i++) {
            int a = 0, b = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    a |= (1 << (c - 'a'));
                } else {
                    b |= (1 << (c - 'A'));
                }
                if (a == b && j - i + 1 > len) {
                    idx = i;
                    len = j - i + 1;
                }
            }
        }
        return idx == -1 ? "" : s.substring(idx, idx + len);
    }
}
