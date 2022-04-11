package com.bingoabin.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author xubin03
 * @date 2021/5/19 6:55 下午
 */
public class ExceptionClass {
	//异常的作用：指示出问题的代码块的位置及异常的内容
	//为什么要有异常：有异常，为什么不直接处理掉，还要throw异常呢？
	// 有些代码自己写了知道怎么使用，大概率不会出错，如果给别人用，别人不知道怎么用，乱传数据，就会出错，给出错误提示

	//Throwable
	//  -- error
	//      -- java.lang.StackOverflowError
	//      -- java.lang.OutOfMemoryError
	//  -- exception

	//编程错误或者偶然的外在因素导致、分为编译时异常、运行时异常
	// 1.编译时异常（checked）：
	// IOException（FileNotFoundException、EOFException、MalformedURLException、UnknowHostException）、ClassNotFoundException、CloneNotSupportedException
	// 2.运行时异常（unchecked）：
	// RuntimeException（NullPointerException、IndexOutOfBoundsException、ClassCastException、NumberFormatException、InputMismatchException、ArithmeticException）
	//
	// 1.RuntimeException：不可预知的，程序应当自行避免，如数组下标越界，访问空指针等
	// 2.非RuntimeException：可预知的，从编译器校验的异常，如ioexception，sqlexception等
	// 总结：前者是程序无法处理的错误，后者是可以处理的异常
	//
	// NullPointerException 空指针异常
	// ClassNotFoundException 指定类不存在
	// NumberFormatException 字符串转换为数字异常
	// IndexOutOfBoundsException 数组下标越界异常
	// ClassCastException 数据类型转换异常
	// FileNotFoundException 文件未找到异常
	// NoSuchMethodException 方法不存在异常
	// IOException IO 异常
	// SocketException Socket 异常

	//常见的Error以及Exception：
	//1.RuntimeException
	//     1.NullPointerException——空指针引用异常
	//     2.ClassCastException——类型强制转换异常
	//     3.IllegalArgumentException——传递非法参数异常
	//     4.IndexOutOfBoundsException——下标越界异常
	//     5.NumberFormatException——数字格式异常
	//2.非RuntimeException
	//     1.ClassNotFoundException——找不到指定class的异常
	//     2.IOException——IO操作异常
	public static void main(String[] args) {
		//编译时异常
		try {
			FileReader file = new FileReader("");
		} catch (FileNotFoundException e) {
			// int i = 2 / 0;
			// e.printStackTrace();
			System.out.println("nihaoya");
		} finally {
		}

		//运行时异常
		int i = 2 / 0;
	}
}
