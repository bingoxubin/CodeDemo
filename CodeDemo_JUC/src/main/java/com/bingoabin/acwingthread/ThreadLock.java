package com.bingoabin.acwingthread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bingoabin
 * @date 2023/5/5 19:53
 * @Description:
 */
public class ThreadLock {
	public static void main(String[] args) throws Exception{
		Worker5 worker1 = new Worker5();
		Worker5 worker2 = new Worker5();
		worker1.start();
		worker2.start();

		worker1.join();
		worker2.join();
		System.out.println("Main Thread Finish");
		System.out.println(Worker5.cnt);

		//解决方式一：线程1start后，立马join 等执行完 再执行下一个线程
		//解决方式二：通过lock
		//解决方式三：通过synchronized
	}
}

class Worker5 extends Thread {
	public static int cnt = 0;
	public static final ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			lock.lock();
			try {
				cnt++;
			}finally {
				lock.unlock();
			}
		}
	}
}
