package com.bingoabin.algorithm.binarysearch;

/**
 * @Author: xubin34
 * @Date: 2022/4/3 8:25 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NextGreatestLetter {
    //Leetcode 744. 寻找比目标字母大的最小字母
    //示例：输入: letters = ["c", "f", "j"]，target = "a"
    //     输出: "c"
    //分析：给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母target，请你寻找在这一有序列表里比目标字母大的最小字母。
    //     在比较时，字母是依序循环出现的。举个例子：
    //     如果目标字母 target = 'z' 并且字符列表为letters = ['a', 'b']，则答案返回'a'
    //思路：
    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        NextGreatestLetter nextGreatestLetter = new NextGreatestLetter();
        System.out.println(nextGreatestLetter.nextGreatestLetter(letters, target));
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.length];
    }

    public char nextGreatestLetter1(char[] letters, char target) {
        int len = letters.length;
        if (letters[len - 1] <= target) {
            return letters[0];
        }
        for (int i = 0; i < len; i++) {
            if (letters[i] <= target) {
                continue;
            } else {
                return letters[i];
            }
        }
        return 'a';
    }
}
