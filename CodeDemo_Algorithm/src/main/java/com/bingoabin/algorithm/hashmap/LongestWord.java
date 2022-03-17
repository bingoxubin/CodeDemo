package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xubin34
 * @Date: 2022/3/17 11:23 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LongestWord {
    //Leetcode 720. 词典中最长的单词
    //示例：输入：words = ["w","wo","wor","worl", "world"]
    //     输出："world"
    //     解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
    //分析：给出一个字符串数组words 组成的一本英语词典。返回words 中最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
    //     若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
    //思路：
    public static void main(String[] args) {
        String[] words = {"w", "wo", "wor", "worl", "world"};
        LongestWord longestWord = new LongestWord();
        System.out.println(longestWord.longestWord(words));
    }

    public String longestWord(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        Set<String> set = new HashSet<>();
        String res = "";
        for (String word : words) {
            if (word.length() <= 1 || set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                if (res.length() < word.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                    res = word;
                }
            }
        }
        return res;
    }
}
