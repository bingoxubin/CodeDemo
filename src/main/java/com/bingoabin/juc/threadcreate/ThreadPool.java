package com.bingoabin.juc.threadcreate;

import java.util.concurrent.*;

/**
 * @author xubin03
 * @date 2021/5/25 4:40 下午
 */

//线程池
// 通过Thread的构造方法去构造线程。在实际的项目中，我们不太会去用这种方式去构造线程，因为它比较耗费资源，频繁调用自然会占用大量系统资源。"而线程池维护着多个线程，等待着监督管理者分配可并发执行的任务。这避免了在处理短时间任务时创建与销毁线程的代价。线程池不仅能够保证内核的充分利用，还能防止过分调度"。
//
// 方式四：使用线程池
// 1、背景：经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。
// 2、思路：提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具。
//
// 好处：
// 1、提高响应速度（减少了创建新线程的时间）
// 2、降低资源消耗（重复利用线程池中线程，不需要每次都创建）
// 3、便于线程管理
// 4、corePoolSize：核心池的大小
// 5、maximumPoolSize：最大线程数
// 6、keepAliveTime：线程没有任务时最多保持多长时间后会终止
//
// 线程池相关API
// 1、JDK 5.0起提供了线程池相关API：ExecutorService 和 Executors
// 2、ExecutorService：真正的线程池接口。常见子类ThreadPoolExecutor
// 3、void execute(Runnable command) ：执行任务/命令，没有返回值，一般用来执行Runnable
// 4、<T> Future<T> submit(Callable<T> task)：执行任务，有返回值，一般又来执行Callable
// 5、void shutdown() ：关闭连接池
// 6、Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池
// 7、Executors.newCachedThreadPool()：创建一个可根据需要创建新线程的线程池
// 8、Executors.newFixedThreadPool(n); 创建一个可重用固定线程数的线程池
// 9、Executors.newSingleThreadExecutor() ：创建一个只有一个线程的线程池
// 10、Executors.newScheduledThreadPool(n)：创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
public class ThreadPool {
	/**
	 * 创建线程的方式四：使用线程池
	 * 好处：
	 * 1.提高响应速度（减少了创建新线程的时间）
	 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
	 * 3.便于线程管理
	 * corePoolSize：核心池的大小
	 * maximumPoolSize：最大线程数
	 * keepAliveTime：线程没有任务时最多保持多长时间后会终止
	 * 面试题：创建多线程有几种方式？四种！
	 * 继承Thread类   实现Runnable接口   实现Callable接口   使用线程池
	 */
	public static void main(String[] args) {
		//核心线程数
		int corePoolSize = 3;
		//最大线程数
		int maximumPoolSize = 6;
		//超过 corePoolSize 线程数量的线程最大空闲时间
		long keepAliveTime = 2;
		//以秒为时间单位
		TimeUnit unit = TimeUnit.SECONDS;
		//创建工作队列，用于存放提交的等待执行任务
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);
		ThreadPoolExecutor threadPoolExecutor = null;
		try {
			//创建线程池
			threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
			                                            maximumPoolSize,
			                                            keepAliveTime,
			                                            unit,
			                                            workQueue,
			                                            new ThreadPoolExecutor.AbortPolicy());

			//循环提交任务
			for (int i = 0; i < 8; i++) {
				//提交任务的索引
				// final int index = (i + 1);
				// threadPoolExecutor.submit(() -> {
				// 	//线程打印输出
				// 	System.out.println("大家好，我是线程：" + index);
				// 	try {
				// 		//模拟线程执行时间，10s
				// 		Thread.sleep(10000);
				// 	} catch (InterruptedException e) {
				// 		e.printStackTrace();
				// 	}
				// });
				threadPoolExecutor.submit(new NumberThread());
				//每个任务提交后休眠500ms再提交下一个任务，用于保证提交顺序
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			threadPoolExecutor.shutdown();
		}
	}

	public static void test() {
		//1. 提供指定线程数量的线程池
		// ExecutorService service = Executors.newFixedThreadPool(10);
		// ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
		//设置线程池的属性
		//System.out.println(service.getClass());
		//service1.setCorePoolSize(15);
		//service1.setKeepAliveTime();

		//2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
		// service.execute(new NumberThread());//适合适用于Runnable
		// service.execute(new NumberThread1());//适合适用于Runnable

		//service.submit(Callable callable);//适合使用于Callable
		//3.关闭连接池
		// service.shutdown();
	}

	static class NumberThread implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 100; i++) {
				if (i % 2 == 0) {
					System.out.println(Thread.currentThread().getName() + ": " + i);
				}
			}
		}
	}

	static class NumberThread1 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 100; i++) {
				if (i % 2 != 0) {
					System.out.println(Thread.currentThread().getName() + ": " + i);
				}
			}
		}
	}
}
