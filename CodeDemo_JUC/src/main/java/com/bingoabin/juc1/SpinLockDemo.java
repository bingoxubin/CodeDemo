package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
class WC {

	AtomicReference<Thread> atomicReference = new AtomicReference<>();

	//锁门
	public void lock() {
		Thread currentThread = Thread.currentThread();
		//判断当前wc中 是否有人使用
		while (!atomicReference.compareAndSet(null, currentThread)) {
			System.out.println(Thread.currentThread().getName() + "等待进入中===");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "====在卫生间中...");
	}

	//开门
	public void unlock() {
		Thread currentThread = Thread.currentThread();
		atomicReference.compareAndSet(currentThread, null);
		System.out.println(currentThread.getName() + "=====unlock...");
	}
}

public class SpinLockDemo {
	public static void main(String[] args) {
		WC wc = new WC();

		new Thread(() -> {
			wc.lock();//锁门
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wc.unlock();//开门
		}, "AA").start();

		new Thread(() -> {
			wc.lock();//锁门
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wc.unlock();//开门
		}, "BB").start();
	}
}
