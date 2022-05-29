package com.bingoabin.deadlocktest;

/**
 * @author bingoabin
 * @date 2022/5/29 11:33
 */
public class DeadLock {
	public static void main(String[] args) {
		DeadLock.deadLock();
	}

	public static void deadLock() {
		final String str1 = "足球";
		final String str2 = "篮球";

		Thread thread1 = new Thread() {
			public void run() {
				synchronized (str1) {
					System.out.println("占据了" + str1);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (str2) {
						System.out.println("想要占据" + str2);
					}
				}
			}
		};

		Thread thread2 = new Thread() {
			public void run() {
				synchronized (str2) {
					System.out.println("占据了" + str2);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (str1) {
						System.out.println("想要占据" + str1);
					}
				}
			}
		};

		thread1.start();
		thread2.start();
	}
}
