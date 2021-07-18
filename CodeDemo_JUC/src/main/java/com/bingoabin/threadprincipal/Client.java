package com.bingoabin.threadprincipal;

import com.bingoabin.juc.threadprincipal.MyExecutor;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author xubin03
 * @date 2021/3/3 10:17 上午
 */
public class Client {
	public static void main(String[] args) {
		MyExecutor executor = new MyExecutor(5);

		Stream.iterate(1, item -> item + 1).limit(10).forEach(
				item -> {
					executor.execute(() -> {
						try {
							System.out.println(Thread.currentThread().getName() + " execute this task");
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					});
				}
		);
	}
}
