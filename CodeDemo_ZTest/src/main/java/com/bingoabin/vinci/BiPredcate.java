package com.bingoabin.vinci;

import java.util.function.BiPredicate;

/**
 * @Author: xubin34
 * @Date: 2022/2/22 3:20 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class BiPredcate {
    /**
     * BiPredicate的test()方法接受两个参数，x和y，具体实现为x.equals(y)，
     * 满足Lambda参数列表中的第一个参数是实例方法的参数调用者，而第二个参数是实例方法的参数，因此可以使用对象方法引用。
     */
    public static void main(String[] args) {

        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp1 = String::equals;

        boolean test = bp1.test("xy", "xy");
        System.out.println(test);
    }
}
