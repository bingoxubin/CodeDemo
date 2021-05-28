package com.bingoabin.juc.threadcreate;

/**
 * @author xubin03
 * @date 2021/5/25 3:28 下午
 */

//继承Thread类
// 多线程的创建，方式一：继承于Thread类
// 		1. 创建一个继承于Thread类的子类
// 		2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
// 		3. 创建Thread类的子类的对象
// 		4. 通过此对象调用start()
public class ThreadTest {
	public static void main(String[] args) {
		//方式一：
		Thread1 thread1 = new Thread1();
		thread1.start();

		//方式二：
		new Thread() {
			@Override
			public void run() {

			}
		}.start();
	}

	static class Thread1 extends Thread {
		@Override
		public void run() {
			//......
		}
	}
}


