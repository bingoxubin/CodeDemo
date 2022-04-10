package com.bingoabin.algorithm.hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xubin34
 * @Date: 2022/4/10 12:41 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class UniqueMorseRepresentations {
    //Leetcode 804. 唯一摩尔斯密码词
    //示例：输入: words = ["gin", "zen", "gig", "msg"]
    //     输出: 2
    //     解释:
    //     各单词翻译如下:
    //     "gin" -> "--...-."
    //     "zen" -> "--...-."
    //     "gig" -> "--...--."
    //     "msg" -> "--...--."
    //     共有 2 种不同翻译, "--...-." 和 "--...--.".
    //分析：国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，比如:
    //     'a' 对应 ".-" ，
    //     'b' 对应 "-..." ，
    //     'c' 对应 "-.-." ，以此类推。
    //     为了方便，所有 26 个英文字母的摩尔斯密码表如下：
    //     [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
    //     给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。
    //     例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单词翻译 。
    //     对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。
    //思路：
    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        UniqueMorseRepresentations uniqueMorseRepresentations = new UniqueMorseRepresentations();
        System.out.println(uniqueMorseRepresentations.uniqueMorseRepresentations(words));
    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> morseSet = new HashSet<>();
        int count = 0;
        for (String word : words) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < word.length(); i++) {
                buffer.append(morse[word.charAt(i) - 'a']);
            }
            if (morseSet.add(buffer.toString())) count++;
        }
        return count;
    }
}
