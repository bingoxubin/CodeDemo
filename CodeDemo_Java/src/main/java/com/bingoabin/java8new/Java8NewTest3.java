package com.bingoabin.java8new;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: xubin34
 * @Date: 2021/8/23 11:21 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Java8NewTest3 {
	public static void main(String[] args) {
		//测试：stream
		List<String> strings = Arrays.asList("abc", "", "bc", "cde", "ijk", "", "jdk");
		List<String> collect = strings.stream().filter(e -> !e.isEmpty()).collect(Collectors.toList());
		System.out.println(collect);

		//forEach   random.ints()表示生成一个无穷无尽的int流
		SecureRandom random = new SecureRandom();
		random.ints(0, 100).limit(10).forEach(System.out::println);

		//map
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<Integer> collect1 = numbers.stream().map(e -> e * e).distinct().collect(Collectors.toList());
		System.out.println(collect1);

		//filter
		long count = strings.stream().filter(e -> e.isEmpty()).count();
		System.out.println(count);

		//limit
		random.ints(0, 10).limit(20).forEach(System.out::print);

		//sorted
		random.ints(0, 20).limit(10).sorted().forEach(System.out::println);

		//parallel 并行
		long count1 = strings.parallelStream().filter(e -> e.isEmpty()).count();
		System.out.println(count1);

		//Collectors Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
		List<String> collect2 = strings.stream().filter(e -> !e.isEmpty()).collect(Collectors.toList());
		System.out.println(collect2);
		String collect3 = strings.stream().filter(e -> !e.isEmpty()).collect(Collectors.joining(","));
		System.out.println(collect3);

		//统计  一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
		List<Integer> numbers1 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		IntSummaryStatistics stats = numbers1.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("所有数之和 : " + stats.getSum());
		System.out.println("平均数 : " + stats.getAverage());
	}

	@Test
	public static void test() {
		System.out.println("使用 Java 7: ");

		// 计算空字符串
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		System.out.println("列表: " + strings);
		long count = getCountEmptyStringUsingJava7(strings);

		System.out.println("空字符数量为: " + count);
		count = getCountLength3UsingJava7(strings);

		System.out.println("字符串长度为 3 的数量为: " + count);

		// 删除空字符串
		List<String> filtered = deleteEmptyStringsUsingJava7(strings);
		System.out.println("筛选后的列表: " + filtered);

		// 删除空字符串，并使用逗号把它们合并起来
		String mergedString = getMergedStringUsingJava7(strings, ", ");
		System.out.println("合并字符串: " + mergedString);
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

		// 获取列表元素平方数
		List<Integer> squaresList = getSquares(numbers);
		System.out.println("平方数列表: " + squaresList);
		List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

		System.out.println("列表: " + integers);
		System.out.println("列表中最大的数 : " + getMax(integers));
		System.out.println("列表中最小的数 : " + getMin(integers));
		System.out.println("所有数之和 : " + getSum(integers));
		System.out.println("平均数 : " + getAverage(integers));
		System.out.println("随机数: ");

		// 输出10个随机数
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < 10; i++) {
			System.out.println(random.nextInt());
		}

		System.out.println("使用 Java 8: ");
		System.out.println("列表: " + strings);

		count = strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println("空字符串数量为: " + count);

		count = strings.stream().filter(string -> string.length() == 3).count();
		System.out.println("字符串长度为 3 的数量为: " + count);

		filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选后的列表: " + filtered);

		mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("合并字符串: " + mergedString);

		squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.println("Squares List: " + squaresList);
		System.out.println("列表: " + integers);

		IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("所有数之和 : " + stats.getSum());
		System.out.println("平均数 : " + stats.getAverage());
		System.out.println("随机数: ");

		random.ints().limit(10).sorted().forEach(System.out::println);

		// 并行处理
		count = strings.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println("空字符串的数量为: " + count);
	}

	private static int getCountEmptyStringUsingJava7(List<String> strings) {
		int count = 0;

		for (String string : strings) {

			if (string.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	private static int getCountLength3UsingJava7(List<String> strings) {
		int count = 0;

		for (String string : strings) {

			if (string.length() == 3) {
				count++;
			}
		}
		return count;
	}

	private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
		List<String> filteredList = new ArrayList<String>();

		for (String string : strings) {

			if (!string.isEmpty()) {
				filteredList.add(string);
			}
		}
		return filteredList;
	}

	private static String getMergedStringUsingJava7(List<String> strings, String separator) {
		StringBuilder stringBuilder = new StringBuilder();

		for (String string : strings) {

			if (!string.isEmpty()) {
				stringBuilder.append(string);
				stringBuilder.append(separator);
			}
		}
		String mergedString = stringBuilder.toString();
		return mergedString.substring(0, mergedString.length() - 2);
	}

	private static List<Integer> getSquares(List<Integer> numbers) {
		List<Integer> squaresList = new ArrayList<Integer>();

		for (Integer number : numbers) {
			Integer square = new Integer(number.intValue() * number.intValue());

			if (!squaresList.contains(square)) {
				squaresList.add(square);
			}
		}
		return squaresList;
	}

	private static int getMax(List<Integer> numbers) {
		int max = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {

			Integer number = numbers.get(i);

			if (number.intValue() > max) {
				max = number.intValue();
			}
		}
		return max;
	}

	private static int getMin(List<Integer> numbers) {
		int min = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {
			Integer number = numbers.get(i);

			if (number.intValue() < min) {
				min = number.intValue();
			}
		}
		return min;
	}

	private static int getSum(List numbers) {
		int sum = (int) (numbers.get(0));

		for (int i = 1; i < numbers.size(); i++) {
			sum += (int) numbers.get(i);
		}
		return sum;
	}

	private static int getAverage(List<Integer> numbers) {
		return getSum(numbers) / numbers.size();
	}
}
