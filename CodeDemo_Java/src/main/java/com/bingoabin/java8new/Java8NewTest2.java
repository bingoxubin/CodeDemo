package com.bingoabin.java8new;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: xubin34
 * @Date: 2021/8/23 10:43 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Java8NewTest2 {
	public static void main(String[] args) {
		// 测试：Optional.ofNullable
		// List<String> list = null;
		// list.forEach(e -> System.out.println(e));

		List<String> list = null;
		List<String> newList = Optional.ofNullable(list).orElse(Lists.newArrayList());
		newList.forEach(e -> System.out.println(e));

		//测试：Function.identity()
		Stream<String> stream = Stream.of("I", "love", "you", "too");
		Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(map);
		//identity()就是Function接口的一个静态方法。Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式
	}
}
