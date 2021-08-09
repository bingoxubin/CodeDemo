package com.bingoabin.threadvolatile;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 2:38 下午
 * @CopyRight: sankuai.com
 * @Description: 通过Lock锁来实现线程安全
 */
public class TestVolatile_AtomicityLock {
	//通过 Lock方式来解决
	public int inc = 0;
	Lock lock = new ReentrantLock();

	public void increase() {
		lock.lock();
		try {
			inc++;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		TestVolatile_AtomicityLock tal = new TestVolatile_AtomicityLock();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 10000; j++) {
						tal.increase();
					}
				}
			}.start();
		}
		while (Thread.activeCount() > 2) {
			Thread.yield();
		}
		System.out.println(tal.inc);
	}
}
