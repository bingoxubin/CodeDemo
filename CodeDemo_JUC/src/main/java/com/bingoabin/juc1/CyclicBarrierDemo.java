package com.bingoabin.juc1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
			System.out.println("======出发去 三亚旅游....");
		});

		for (int i = 1; i <= 10; i++) {
			final int temp = i;
			new Thread(() -> {
				//第几个人 已报名...
				try {
					TimeUnit.SECONDS.sleep(temp);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("第" + temp + "个人 已报名...");

				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
