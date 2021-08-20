package com.bingoabin.juc1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其他线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);

		for (int i = 1; i <= 6; i++) {
			final int temp = i;
			new Thread(() -> {
				try {
					TimeUnit.SECONDS.sleep(temp);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("同学离开了教室");
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}

		//大叔等待关门
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName() + "大叔 最后关门...");
	}
}
