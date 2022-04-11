package com.bingoabin.ProducerConsumer;

/**
 * @author bingoabin
 * @date 2021/6/13 21:54
 */
//创建生产者，不断生产，死循环，怎么生产，调用共享数据中的同步方法
public class Producer implements Runnable {
	private Bread bread;

	public Producer(Bread bread) {
		this.bread = bread;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				bread.push();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
