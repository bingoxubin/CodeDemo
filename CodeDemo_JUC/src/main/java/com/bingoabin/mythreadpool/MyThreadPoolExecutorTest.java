package com.bingoabin.mythreadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bingoabin
 * @date 2021/6/17 17:39
 */
public class MyThreadPoolExecutorTest {
	public static void main(String[] args) {
		Executor threadPool = new MyThreadPoolExecutor("test", 5, 10, new ArrayBlockingQueue<>(15), new DiscardRejectPolicy());
		AtomicInteger num = new AtomicInteger(0);

		for (int i = 0; i < 100; i++) {
			threadPool.execute(() -> {
				try {
					Thread.sleep(1000);
					System.out.println("running: " + System.currentTimeMillis() + ": " + num.incrementAndGet());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
