package com.bingoabin.juc.ProducerConsumer;

/**
 * @author bingoabin
 * @date 2021/6/13 21:55
 */
public class Test {
	public static void main(String[] args) {
		Bread bread = new Bread(0);

		Producer producer = new Producer(bread);
		Consumer consumer = new Consumer(bread);

		Thread thread1 = new Thread(producer,"生产者");
		Thread thread2 = new Thread(consumer,"消费者");

		thread1.start();
		thread2.start();
	}
}
