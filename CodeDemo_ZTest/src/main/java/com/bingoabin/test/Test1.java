package com.bingoabin.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xubin34
 * @Date: 2021/12/27 3:33 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Test1 {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>(list);
        System.out.println(set);
    }
}
