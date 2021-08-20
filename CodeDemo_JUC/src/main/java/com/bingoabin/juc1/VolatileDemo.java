package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile关键字的使用
 */
class MyData {

	//int number = 0;
	volatile int number = 0;

	AtomicInteger atomicInteger = new AtomicInteger();

	public void setTo60() {
		this.number = 60;
	}

	public void addPlusPlus() {
		number++;
	}

	public void atomicPlusPlus() {
		atomicInteger.getAndIncrement();
	}
}

public class VolatileDemo {

	public static void main(String[] args) {
		//volatileVisibilityDemo();
		atomicDemo();
	}

	/**
	 * 原子性测试
	 * 需求: 启动20个线程, 每个线程执行1000次 number++操作, 问最终20个线程执行完毕后, number值是多少?
	 */
	private static void atomicDemo() {
		System.out.println("===原子性测试====");

		MyData myData = new MyData();

		for (int i = 1; i <= 20; i++) {
			new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					myData.addPlusPlus();
					myData.atomicPlusPlus();
				}
			}, String.valueOf(i)).start();

		}

		//main线程
		//保证上面的线程已经执行完毕, 再进行main线程的执行, 执行线程礼让操作
		while (Thread.activeCount() > 2) {
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName() + "\t main线程中获取number值" + myData.number);
		System.out.println(Thread.currentThread().getName() + "\t main线程中获取atomic中的number值" + myData.atomicInteger);

	}

	/**
	 * volatile 可以保证可见性, 及时通知其他线程 主物理内存的值 已被修改
	 */
	private static void volatileVisibilityDemo() {
		System.out.println("===可见性测试====");

		MyData myData = new MyData();

		//启动一个线程 操作主内存中的共享数据 number
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 执行");

			//更新number的值
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myData.setTo60();

			System.out.println(Thread.currentThread().getName() + "\t 更新number值" + myData.number);
		}).start();

		//main线程
		//保证上面的线程已经执行完毕, 再进行main线程的执行
		while (myData.number == 0) {
			System.out.println("main-----");
		}
		System.out.println(Thread.currentThread().getName() + "\t main线程中获取number值" + myData.number);

	}

}
