package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2022/2/3 11:50 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ReversePrefix {
    //Leetcode 2000. 反转单词前缀
    //示例：示例 1：
    //     输入：word = "abcdefd", ch = "d"
    //     输出："dcbaefd"
    //     解释："d" 第一次出现在下标 3 。
    //     反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
    //分析：给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
    //     例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
    //     返回 结果字符串 。
    //示例：
    public static void main(String[] args) {
        String word = "abcdefd";
        char ch = 'd';
        ReversePrefix reversePrefix = new ReversePrefix();
        System.out.println(reversePrefix.reversePrefix(word, ch));
    }

    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index >= 0) {
            char[] arr = word.toCharArray();
            int left = 0;
            int right = index;
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
            word = new String(arr);
        }
        return word;
    }
}
