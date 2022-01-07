package com.bingoabin.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/1/7 5:11 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestListToString {
    public static void main(String[] args) {
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("List转字符串");
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        System.out.println(list1.toString());
        String ss = String.join(",", list1);
//        System.out.println(StringUtils.join("", list1));
        System.out.println(ss);
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("字符串转List");
        List<String> listString = Arrays.asList(ss.split(","));
        for (String string : listString) {
            System.out.println(string);
        }
        System.out.println("+++++++++++++++++++++++++++++++++");
    }
}
