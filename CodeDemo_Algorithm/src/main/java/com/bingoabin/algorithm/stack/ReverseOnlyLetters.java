package com.bingoabin.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: xubin34
 * @Date: 2022/2/23 9:51 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ReverseOnlyLetters {
    //Leetcode 917. 仅仅反转字母
    //示例：输入：s = "a-bC-dEf-ghIj"
    //     输出："j-Ih-gfE-dCba"
    //分析：给你一个字符串 s ，根据下述规则反转字符串：
    //     所有非英文字母保留在原有位置。
    //     所有英文字母（小写或大写）位置反转。
    //     返回反转后的 s 。
    //思路：
    public static void main(String[] args) {
        String s = "a-bC-dEf-ghIj";
        ReverseOnlyLetters reverseOnlyLetters = new ReverseOnlyLetters();
        System.out.println(reverseOnlyLetters.reverseOnlyLetters(s));
        System.out.println(reverseOnlyLetters.reverseOnlyLetters1(s));
        System.out.println(reverseOnlyLetters.reverseOnlyLetters2(s));
        System.out.println(reverseOnlyLetters.reverseOnlyLetters3(s));
    }

    //方式一：双指针
    public String reverseOnlyLetters1(String S) {
        char[] res = S.toCharArray();
        int left = 0;
        int right = res.length - 1;
        while (left < right) {
            while (left < right && !Character.isLetter(res[left])) left++;
            while (left < right && !Character.isLetter(res[right])) right--;
            if (left < right) {
                char temp = res[left];
                res[left] = res[right];
                res[right] = temp;
                left++;
                right--;
            }
        }
        return new String(res);
    }

    //方式二：单调栈
    public String reverseOnlyLetters(String S) {
        Stack<Character> letters = new Stack();
        for (char c : S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }

    //自己实现 单调栈
    public String reverseOnlyLetters2(String s) {
        Deque<Character> letters = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                letters.push(c);
            }
        }
        StringBuffer res = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                res.append(letters.pop());
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    //自己实现 双指针
    public String reverseOnlyLetters3(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] letters = s.toCharArray();
        while (left < right) {
            while (left < right && !Character.isLetter(letters[left])) left++;
            while (left < right && !Character.isLetter(letters[right])) right--;
            if (left < right) {
                char temp = letters[left];
                letters[left] = letters[right];
                letters[right] = temp;
                left++;
                right--;
            }
        }
        return new String(letters);
    }
}
