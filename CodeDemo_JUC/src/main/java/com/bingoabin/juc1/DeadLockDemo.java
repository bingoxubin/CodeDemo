package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;

class ThreadTask implements Runnable {

	private String lockAA;
	private String lockBB;

	public ThreadTask(String lockA, String lockB) {
		this.lockAA = lockA;
		this.lockBB = lockB;

	}

	@Override
	public void run() {
		synchronized (lockAA) {
			System.out.println(Thread.currentThread().getName() + "\t 获取到了" + lockAA + ", 尝试获取" + lockBB);

			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (lockBB) {
				System.out.println(Thread.currentThread().getName() + "\t 获取到了" + lockBB + " 加起来池一口肉..");
			}
		}
	}
}

public class DeadLockDemo {
	public static void main(String[] args) {
		String lockA = "lockA=====";
		String lockB = "lockB=====";

		new Thread(new ThreadTask(lockA, lockB), "ThreadA").start();
		new Thread(new ThreadTask(lockB, lockA), "ThreadB").start();
	}
}
