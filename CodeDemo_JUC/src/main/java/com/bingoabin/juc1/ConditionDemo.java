package com.bingoabin.juc1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * A打印5次，B打印10次，C打印15次
 * 接着
 * A打印5次，B打印10次，C打印15次
 * 来10轮
 */
class ShapeData {

	Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();

	/**
	 * 用于表示状态的变化, 1==> c1;  2==>c2;  3==>c3
	 */
	int state = 1;

	/**
	 * A打印5次
	 */
	public void printC1() {
		lock.lock();
		try {
			//1.判断
			if (state != 1) {
				c1.await();
			}
			//2.干活
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			//3.通知B. state更新为2
			state = 2;
			c2.signal();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	/**
	 * B打印10次
	 */
	public void printC2() {
		lock.lock();
		try {
			//1.判断
			if (state != 2) {
				c2.await();
			}
			//2.干活
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			//3.通知C. state更新为3
			state = 3;
			c3.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * C打印15次
	 */
	public void printC3() {
		lock.lock();
		try {
			//1.判断
			if (state != 3) {
				c3.await();
			}
			//2.干活
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			//3.通知A. state更新为1
			state = 1;
			c1.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}

public class ConditionDemo {
	public static void main(String[] args) {
		ShapeData shapeData = new ShapeData();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				shapeData.printC1();
			}
		}, "A").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				shapeData.printC2();
			}
		}, "B").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				shapeData.printC3();
			}
		}, "C").start();
	}
}
