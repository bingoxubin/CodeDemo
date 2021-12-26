package com.bingoabin.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/12/26 11:35 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindThirdWords {
    //LeetCode 1078. Bigram 分词
    //示例：输入：text = "alice is a good girl she is a good student", first = "a", second = "good"  输出：["girl","student"]
    //分析：给出第一个词first 和第二个词second，考虑在某些文本text中可能以 "first second third" 形式出现的情况，其中second紧随first出现，third紧随second出现。
    //     对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
    //思路：
    public static void main(String[] args) {
        String text = "alice is a good girl she is a good student";
        String first = "a";
        String second = "good";
        FindThirdWords findThirdWords = new FindThirdWords();
        System.out.println(Arrays.toString(findThirdWords.findOcurrences(text, first, second)));
    }

    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length - 2; i++) {
            if (words[i].equals(first) && words[i + 1].equals(second)) {
                res.add(words[i + 2]);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
