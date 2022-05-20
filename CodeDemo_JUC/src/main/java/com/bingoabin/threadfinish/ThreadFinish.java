package com.bingoabin.threadfinish;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bingoabin
 * @date 2022/5/19 21:16
 */
public class ThreadFinish {
	public static void main(String[] args) throws InterruptedException{
		ExecutorService thread = Executors.newFixedThreadPool(10);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		thread.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		countDownLatch.await();
		thread.shutdown();
	}
}
