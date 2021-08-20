package com.bingoabin.juc1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 */
class PhonePlus {
	public synchronized void sendEmail() {
		System.out.println(Thread.currentThread().getName() + "=====sendEmail.....");

		sendMessage();
	}

	public synchronized void sendMessage() {
		System.out.println(Thread.currentThread().getName() + "=====sendMessage....");
	}

	Lock lock = new ReentrantLock();

	public void method01() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "=====method01.....");

			method02();
		} finally {
			lock.unlock();
		}

	}

	public void method02() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "=====method02.....");

		} finally {
			lock.unlock();
		}

	}
}

public class ReentrantLockDemo {
	public static void main(String[] args) {
		PhonePlus phonePlus = new PhonePlus();

		new Thread(() -> {
			//phonePlus.sendEmail();
			phonePlus.method01();
		}, "A").start();

		new Thread(() -> {
			//phonePlus.sendEmail();
			phonePlus.method01();
		}, "B").start();
	}

}
