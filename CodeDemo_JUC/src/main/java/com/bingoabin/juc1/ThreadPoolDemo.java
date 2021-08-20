package com.bingoabin.juc1;

import java.util.concurrent.*;

/**
 * 线程池代码演示
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		//System.out.println("======FixedThreadPool线程池====");
		//特点：执行长期任务性能好，创建一个线程池，一池有N个固定的线程，有固定线程数的线程
		//一个线程池中有5个工作线程, 类似于银行有5个受理窗口
		// threadPoolTask(Executors.newFixedThreadPool(5));

		//System.out.println("====newSingleThreadExecutor线程池===");
		//特点：一个任务一个任务的执行，一池一线程
		//一个线程池中有1个工作线程, 类似于银行有1个受理窗口
		//threadPoolTask(Executors.newSingleThreadExecutor());

		//System.out.println("====newCachedThreadPool线程池===");
		//特点：执行很多短期异步任务，线程池根据需要创建新线程，但在先前构建的线程可用时将重用他们。可扩容，遇强则强
		//threadPoolTask(Executors.newCachedThreadPool());

		System.out.println("====Custom Thread Pool线程池===");

		threadPoolTask(new ThreadPoolExecutor(
				2,
				4,
				1L,
				TimeUnit.SECONDS,
				new LinkedBlockingDeque<>(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.DiscardPolicy()
		));
	}

	public static void threadPoolTask(ExecutorService threadPool) {
		//模拟有10个顾客来办理业务
		try {
			for (int i = 1; i <= 10; i++) {
				threadPool.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "\t 办理业务");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}
}
