package com.bingoabin.threadlocal;

/**
 * @author bingoabin
 * @date 2022/9/13 10:47
 * @Description:
 */
public class ThreadLocalHelloWorld {
	static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {{
		this.set("main");
	}};

	public static void main(String[] args) {
		System.out.printf("the threadlocal of %s 's value is %s\n'", Thread.currentThread().getName(), threadLocal.get());
		Thread zhangxiaomin, zhangsan;
		(zhangxiaomin = new Thread(() -> {
			System.out.printf("the threadlocal of %s 's value is %s\n'", Thread.currentThread().getName(), threadLocal.get());
			threadLocal.set("zhangxiaomin");
			System.out.printf("the threadlocal of %s 's value is %s\n'", Thread.currentThread().getName(), threadLocal.get());
		}, "zhangxiaomin")).start();
		(zhangsan = new Thread(() -> {
			System.out.printf("the threadlocal of %s 's value is %s\n'", Thread.currentThread().getName(), threadLocal.get());
			threadLocal.set("zhangsan");
			System.out.printf("the threadlocal of %s 's value is %s\n'", Thread.currentThread().getName(), threadLocal.get());
		}, "zhangsan")).start();
	}
}
