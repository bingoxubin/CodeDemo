package com.bingoabin.test;

import java.util.HashSet;

/**
 * @Author: xubin34
 * @Date: 2021/9/26 5:19 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestHashSet {
	public static void main(String[] args){
		HashSet<Long> set = new HashSet<>();
		set.forEach(e -> System.out.println(e));
	}
}
