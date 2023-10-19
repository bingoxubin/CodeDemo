package com.bingoabin.java8new;

import org.junit.Test;

import java.util.function.*;

/**
 * @author bingoabin
 * @date 2023/10/19 9:12:32
 * @Description:
 */
public class NewOperation {
	//Predicate 断言，是一个函数式接口，它接收一个参数并返回一个布尔值
	@Test
	public void test1() {
		Predicate<String> predicate = s -> s.length() > 4;
		System.out.println(predicate.test("test"));
		System.out.println(predicate.test("hello"));
	}

	//Function 是一个函数式接口，它接收一个参数并返回一个结果
	@Test
	public void test2() {
		Function<String, Integer> function = s -> s.length();
		System.out.println(function.apply("hello"));
		System.out.println(function.apply("test"));
	}

	//Consumer 是一个函数式接口，它接受一个参数没有返回值
	@Test
	public void test3(){
		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("test");
		consumer.accept("hello");
	}

	//Supplier 是一个函数式接口，它不接收参数，返回一个结果
	@Test
	public void test4(){
		Supplier<String> supplier = () -> "hello world!";
		System.out.println(supplier.get());
	}

	//UnaryOperator 是一个函数式接口，它接收一个参数，返回一个参数。接收一个T类型的参数并返回一个T类型的结果。
	@Test
	public void test5(){
		UnaryOperator<String> unaryOperator = s -> s.toUpperCase();
		System.out.println(unaryOperator.apply("test"));
		System.out.println(unaryOperator.apply("Hello"));
	}

	//BinaryOperator 接收两个T类型的参数并返回一个T类型的结果。
	@Test
	public void test6(){
		BinaryOperator<String> binaryOperator = (s1,s2) -> s1 + s2;
		System.out.println(binaryOperator.apply("test","hello"));
	}

	//还有很多
}
