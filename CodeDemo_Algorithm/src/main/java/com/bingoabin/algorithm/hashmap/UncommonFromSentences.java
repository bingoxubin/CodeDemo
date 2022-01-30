package com.bingoabin.algorithm.hashmap;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2022/1/30 11:21 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class UncommonFromSentences {
    //Leetcode 884. 两句话中的不常见单词
    //示例：示例 1：
    //     输入：s1 = "this apple is sweet", s2 = "this apple is sour"
    //     输出：["sweet","sour"]
    //分析：句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
    //     如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
    //     给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
    //思路：hashmap计数
    public static void main(String[] args) {
        String A = "this apple is sweet";
        String B = "this apple is sour";
        UncommonFromSentences uncommonFromSentences = new UncommonFromSentences();
        System.out.println(Arrays.toString(uncommonFromSentences.uncommonFromSentences(A, B)));
    }

    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : A.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String word : B.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> res = new LinkedList<>();
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                res.add(key);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
