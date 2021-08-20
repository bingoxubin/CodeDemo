package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock2Demo {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "\t coming...");
				condition.await();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			System.out.println(Thread.currentThread().getName() + "\t end...");
		}, "A").start();

		new Thread(() -> {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "\t 唤醒线程...");
				condition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "B").start();
	}
}
