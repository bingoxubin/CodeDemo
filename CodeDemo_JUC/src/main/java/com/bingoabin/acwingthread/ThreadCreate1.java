package com.bingoabin.acwingthread;

/**
 * @author bingoabin
 * @date 2023/5/5 17:14
 * @Description:
 */
public class ThreadCreate1 {
	public static void main(String[] args) throws Exception{
		//方式一：
		Worker worker1 = new Worker();
		Worker3 worker2 = new Worker3();
		worker1.setName("thread-1");
		worker2.setName("thread-2");
		worker1.start();
		worker2.start();

		//主线程执行到这边会卡住，等线程1执行完毕后，主线程再继续执行
		//join中可以加上时间参数，表示只等这么长时间，超过这个时间就不等了
		worker1.join(5000);
		System.out.println("Main Thread Thread1 不等了");
		worker2.join();
		System.out.println("Main Thread finished");
	}
}

//写法1：继承Thread类
class Worker extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello! " + this.getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

class Worker3 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello! " + this.getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
