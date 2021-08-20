package com.bingoabin.juc1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列模式 生产者消费者案例
 */
class MyResouce {
	private volatile boolean flag = true; //默认开启, 进行进行生产 + 消费
	private AtomicInteger atomicInteger = new AtomicInteger();

	BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

	/**
	 * 生产包子
	 */
	public void prod() throws InterruptedException {
		while (flag) {
			//生产包子
			String data = atomicInteger.incrementAndGet() + ""; // ++i
			boolean resultValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
			if (resultValue) {
				System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "...成功");
			} else {
				System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "...失败");
			}
			//模拟耗时操作
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "\t 老板喊停了, Flag已更新为false, 停止生产");
	}

	/**
	 * 购买包子
	 */
	public void cons() throws InterruptedException {
		while (flag) {
			//购买包子
			String result = blockingQueue.poll(2L, TimeUnit.SECONDS);
			if (result == null || "".equals(result)) {
				//没有包子
				System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟没有购买到包子, 退出消费..");
				return;
			}
			System.out.println(Thread.currentThread().getName() + "\t 购买包子成功...");
		}
	}

	/**
	 * 老板喊停
	 */
	public void stop() {
		//模拟营业时间
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("5秒钟后, 老板喊停...");

		flag = false;
	}
}

public class ProdConsBlockQueueDemo {
	public static void main(String[] args) {
		MyResouce myResouce = new MyResouce();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 生产线程启动...");

			try {
				myResouce.prod();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "师傅A").start();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 生产线程启动...");

			try {
				myResouce.prod();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "师傅B").start();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 消费线程启动...");

			try {
				myResouce.cons();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "====顾客A").start();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 消费线程启动...");

			try {
				myResouce.cons();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "====顾客B").start();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 消费线程启动...");

			try {
				myResouce.cons();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "====顾客C").start();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 消费线程启动...");

			try {
				myResouce.cons();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "====顾客D").start();

		//老板喊停
		myResouce.stop();
	}
}
