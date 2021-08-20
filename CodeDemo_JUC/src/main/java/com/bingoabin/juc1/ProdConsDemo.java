package com.bingoabin.juc1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生成者消费者案例
 */
class MyResource {

	//用于记录生成好的包子数量
	int number = 0;

	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	/**
	 * 生成包子
	 */
	public void increment() throws InterruptedException {

		lock.lock();
		try {
			//1. 判断
			while (number != 0) {
				//this.wait();
				condition.await();
			}
			//2. 干活
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);

			//3.通知对方
			//this.notifyAll();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 消费包子
	 */
	public void decrement() throws InterruptedException {

		lock.lock();
		try {
			//1. 判断
			while (number == 0) {
				//this.wait();
				condition.await();
			}
			//2. 干活
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);

			//3.通知对方
			//this.notifyAll();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

public class ProdConsDemo {
	public static void main(String[] args) {

		MyResource myResource = new MyResource();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					myResource.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "师傅1").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					myResource.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "师傅2").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					myResource.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "顾客1").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					myResource.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "顾客2").start();
	}
}
