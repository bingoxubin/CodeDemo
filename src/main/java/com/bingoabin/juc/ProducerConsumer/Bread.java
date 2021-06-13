package com.bingoabin.juc.ProducerConsumer;

/**
 * @author bingoabin
 * @date 2021/6/13 21:55
 */
//构建共享资源，当资源里面小于20个以后，进行生产，否则的话wait 等待
//构建共享资源，当资源里面大于0，进行消费，否则wait等待
public class Bread {
	private int num;

	public Bread(int num) {
		this.num = num;
	}

	public synchronized void push() throws InterruptedException {
		if (num < 20) {
			num++;
			notify();
			System.out.println(Thread.currentThread().getName() + ":" + num);
		} else {
			wait();
		}
	}

	public synchronized void pop() throws InterruptedException {
		if (num > 0) {
			System.out.println(Thread.currentThread().getName() + ":" + num);
			num--;
			notify();
		} else {
			wait();
		}
	}
}
