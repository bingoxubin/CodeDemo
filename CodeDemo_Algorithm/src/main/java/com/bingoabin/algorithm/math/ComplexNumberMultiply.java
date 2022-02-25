package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/2/25 10:04 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ComplexNumberMultiply {
    //Leetcode 537. 复数乘法
    //示例：输入：num1 = "1+-1i", num2 = "1+-1i"
    //     输出："0+-2i"
    //     解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
    //分析：复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
    //     实部 是一个整数，取值范围是 [-100, 100]
    //     虚部 也是一个整数，取值范围是 [-100, 100]
    //     i2 == -1
    //     给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
    //思路：
    public static void main(String[] args) {
        String num1 = "1+-1i";
        String num2 = "1+-1i";
        ComplexNumberMultiply complexNumberMultiply = new ComplexNumberMultiply();
        System.out.println(complexNumberMultiply.complexNumberMultiply(num1, num2));
    }

    public String complexNumberMultiply(String num1, String num2) {
        //碰到+ 或者 i进行split
        String[] ss1 = num1.split("[+i]"), ss2 = num2.split("[+i]");
        //String[] ss1 = num1.split("\\+|i"), ss2 = num2.split("\\+|i");
        int a = parse(ss1[0]), b = parse(ss1[1]);
        int c = parse(ss2[0]), d = parse(ss2[1]);
        int A = a * c - b * d, B = b * c + a * d;
        return A + "+" + B + "i";
    }

    public int parse(String s) {
        return Integer.parseInt(s);
    }
}
