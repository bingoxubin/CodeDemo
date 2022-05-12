package com.bingoabin.threadabc;

/**
 * @author bingoabin
 * @date 2022/4/19 23:54
 */
public class TestAB {
	public static void main(String[] args) {
		NextPrint nextPrint1 = new NextPrint();

		Thread thread1 = new Thread(nextPrint1);
		Thread thread2 = new Thread(nextPrint1);

		thread1.setName("A");
		thread2.setName("B");

		thread1.start();
		thread2.start();
	}
}

class NextPrint implements Runnable {
	int number = 1;
	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				notify();
				if (number < 100) {
					System.out.println(Thread.currentThread().getName() + ":" + number);
					number++;
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
		}
	}
}
