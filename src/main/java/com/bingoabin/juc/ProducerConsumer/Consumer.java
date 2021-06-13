package com.bingoabin.juc.ProducerConsumer;

/**
 * @author bingoabin
 * @date 2021/6/13 21:55
 */
//创建消费者，不断消费，死循环，怎么消费，调用共享数据中的同步方法
public class Consumer implements Runnable {
	private Bread bread;

	public Consumer(Bread bread) {
		this.bread = bread;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
				bread.pop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
