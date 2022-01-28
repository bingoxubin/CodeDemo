package com.bingoabin.algorithm.doublepoints;

/**
 * @Author: xubin34
 * @Date: 2022/1/28 11:43 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountValidWords {
    //Leetcode 2047. 句子中的有效单词数
    //示例：输入：sentence = "cat and  dog"
    //     输出：3
    //     解释：句子中的有效单词是 "cat"、"and" 和 "dog"
    //分析：句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
    //     如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
    //     1. 仅由小写字母、连字符和/或标点（不含数字）。
    //     2. 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
    //     3.至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
    //     这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
    //     给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
    //思路：
    public static void main(String[] args) {
        String sentence = "cat and  dog";
        CountValidWords countValidWords = new CountValidWords();
        System.out.println(countValidWords.countValidWords(sentence));
        System.out.println(countValidWords.countValidWords1(sentence));
    }

    public int countValidWords(String sentence) {
        String[] ss = sentence.split(" ");
        int res = 0;
        for (String s : ss) {
            if (check(s)) {
                res++;
            }
        }
        return res;
    }

    public boolean check(String s) {
        int n = s.length();
        //如果字符串长度为0，返回false
        if (n == 0) return false;
        //分别使用 c1 和 c2 代表「连字符」和「标点符号」的出现次数。
        for (int i = 0, c1 = 0, c2 = 0; i < n; i++) {
            char c = s.charAt(i);
            //如果出现数字，或者出现空格，返回false
            if (Character.isDigit(c)) return false;
            if (c == ' ') return false;
            //连字符个数，以及位置判断
            if (c == '-' && ++c1 >= 0) {
                if (c1 > 1 || (i == 0 || i == n - 1)) return false;
                if (!Character.isLetter(s.charAt(i - 1)) || !Character.isLetter(s.charAt(i + 1))) return false;
            }
            //标点符号个数，以及位置判断
            if ((c == '!' || c == '.' || c == ',') && ++c2 >= 0) {
                if (c2 > 1 || (i != n - 1)) return false;
            }
        }
        return true;
    }

    //自己实现一遍
    public int countValidWords1(String sentence) {
        int n = sentence.length(), ans = 0;
        for (int i = 0; i < n; ) {
            if (sentence.charAt(i) == ' ' && ++i >= 0) continue;
            int j = i;
            while (j < n && sentence.charAt(j) != ' ') j++;
            if (check1(sentence.substring(i, j))) ans++;
            i = j + 1;
        }
        return ans;
    }

    public boolean check1(String s) {
        int len = s.length();
        if (len == 0) return false;
        //统计连字符的个数；以及标点符号出现的次数
        for (int i = 0, c1 = 0, c2 = 0; i < len; i++) {
            char c = s.charAt(i);
            //出现数字或者空格，直接false
            if (Character.isDigit(c) || c == ' ') return false;
            //连字符不能出现两次,前后只能为小写字符
            if (c == '-' && ++c1 >= 0) {
                if (c1 > 1 || (i == 0 || i == len - 1)) return false;
                if (!Character.isLetter(s.charAt(i - 1)) || !Character.isLetter(s.charAt(i + 1))) return false;
            }
            //标点只能在最后，只能出现一次
            if ((c == '!' || c == '.' || c == ',') && ++c2 >= 0) {
                if (c2 > 1 || (i != len - 1)) return false;
            }
        }
        return true;
    }
}
















