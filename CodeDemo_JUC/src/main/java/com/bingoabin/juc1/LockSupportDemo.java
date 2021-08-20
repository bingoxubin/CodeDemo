package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + "\t coming...");

			LockSupport.park(); //阻塞线程, 要消费permit的值 从1-->0
			//LockSupport.park(); //阻塞线程, 要消费permit的值 从1-->0

			System.out.println(Thread.currentThread().getName() + "\t 被T2线程唤醒了...");
		}, "汽车");
		t1.start();

		Thread t2 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 唤醒T1线程");

			LockSupport.unpark(t1);
			//LockSupport.unpark(t1);
		}, "停车场拦截杆");
		t2.start();
	}
}
