package com.bingoabin.ProducerConsumer;

/**
 * @author bingoabin
 * @date 2021/6/13 21:55
 */
public class Test {
	//构建测试方法，先创建共享资源，作为同步锁，分别创建生产者消费者，然后启动生产者消费者线程，可以对生产者消费者重命名
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
