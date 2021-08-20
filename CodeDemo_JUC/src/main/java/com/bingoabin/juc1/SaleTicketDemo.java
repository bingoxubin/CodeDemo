package com.bingoabin.juc1;

import java.util.concurrent.locks.ReentrantLock;

class Ticket {
	//票
	private int number = 30;
	//锁
	private final ReentrantLock lock = new ReentrantLock();

	public void saleTicket() {

		//获取锁
		lock.lock();
		try {
			if (number > 0) {
				System.out.println(Thread.currentThread().getName() + "\t 卖出第:" + (number--) + "\t 还剩下:" + number);
			}
		} finally {
			//释放锁
			lock.unlock();
		}

	}
}

/**
 * 题目: 三个售票员, 共计要卖出30张票
 */
public class SaleTicketDemo {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();

		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				ticket.saleTicket();
			}
		}, "窗口A").start();

		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				ticket.saleTicket();
			}
		}, "窗口B").start();

		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				ticket.saleTicket();
			}
		}, "窗口C").start();
	}
}
