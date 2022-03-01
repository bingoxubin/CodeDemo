package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2022/3/1 9:56 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ZConvert {
    //Leetcode 6. Z 字形变换
    //示例：示例 2：
    //     输入：s = "PAYPALISHIRING", numRows = 4
    //     输出："PINALSIGYAHRPI"
    //     解释：
    //     P     I    N
    //     A   L S  I G
    //     Y A   H R
    //     P     I
    //分析：将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
    //     比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
    //     P   A   H   N
    //     A P L S I I G
    //     Y   I   R
    //     之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
    //     请你实现这个将字符串进行指定行数变换的函数：
    //     string convert(string s, int numRows);
    //思路：
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        ZConvert zConvert = new ZConvert();
        System.out.println(zConvert.convert(s,4));
    }

    public String convert(String s, int numRows) {
        char[][] arr = new char[numRows][s.length()];
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int col = 0;
        int row = 0;
        if (s.length() <= 2 || numRows == 1) {
            return s;
        }
        while (index < s.length()) {
            while (row < numRows && index < s.length()) {
                arr[row++][col] = s.charAt(index++);
            }
            row--;
            while (row > 0 && index < s.length()) {
                arr[--row][++col] = s.charAt(index++);
            }
            row++;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != '\u0000') {
                    sb.append(arr[i][j]);
                }
            }
        }

        return sb.toString();
    }
}
