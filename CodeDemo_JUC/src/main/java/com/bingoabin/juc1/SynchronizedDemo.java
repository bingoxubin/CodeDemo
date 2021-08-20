package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {

	/**
	 * 让线程等待
	 */
	public void waitThread() {
		synchronized (this) {
			try {
				System.out.println(Thread.currentThread().getName() + "\t coming...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t end....");
		}
	}

	/**
	 * 唤醒线程
	 */
	public void notifyThread() {
		synchronized (this) {
			System.out.println("唤醒线程....");
			notify();
		}
	}

	public static void main(String[] args) {
		SynchronizedDemo synchronizedDemo = new SynchronizedDemo();

		new Thread(() -> {
			//让线程等待
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronizedDemo.waitThread();
		}, "A").start();

		new Thread(() -> {
			//唤醒线程

			synchronizedDemo.notifyThread();
		}, "B").start();
	}
}
