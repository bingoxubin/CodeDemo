package com.bingoabin.juc.ProducerConsumer;

/**
 * @author bingoabin
 * @date 2021/6/13 21:54
 */
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
