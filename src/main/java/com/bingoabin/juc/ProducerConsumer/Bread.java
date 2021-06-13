package com.bingoabin.juc.ProducerConsumer;

/**
 * @author bingoabin
 * @date 2021/6/13 21:55
 */
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
