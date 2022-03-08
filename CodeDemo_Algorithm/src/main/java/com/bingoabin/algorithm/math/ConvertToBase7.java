package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/3/8 9:53 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ConvertToBase7 {
    //Leetcode 504. 七进制数
    //示例：输入: num = 100
    //     输出: "202"
    //     输入: num = -7
    //     输出: "-10"
    //分析：给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
    //思路：
    public static void main(String[] args) {
        ConvertToBase7 convertToBase7 = new ConvertToBase7();
        System.out.println(convertToBase7.convertToBase7(100));
        System.out.println(convertToBase7.convertToBase7_copy(100));
    }

    public String convertToBase7(int num) {
        if (num == 0) return "0";
        StringBuffer result = new StringBuffer();
        int tempnum = Math.abs(num);
        while (tempnum > 0) {
            result.append(tempnum % 7);
            tempnum /= 7;
        }
        if (num < 0) return result.reverse().insert(0, "-").toString();
        return result.reverse().toString();
    }

    //自己实现一遍
    public String convertToBase7_copy(int num) {
        if (num == 0) return "0";
        int abs = Math.abs(num);
        StringBuilder res = new StringBuilder();
        while (abs > 0) {
            res.append(abs % 7);
            abs /= 7;
        }
        if (num < 0) res.reverse().insert(0, "-").toString();
        return res.toString();
    }
}
