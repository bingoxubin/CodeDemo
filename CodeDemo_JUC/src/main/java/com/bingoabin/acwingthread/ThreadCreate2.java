package com.bingoabin.acwingthread;

/**
 * @author bingoabin
 * @date 2023/5/5 18:59
 * @Description:
 */
public class ThreadCreate2 {
	public static void main(String[] args){
		//方式二：
		new Thread(new Worker1()).start();
		new Thread(new Worker2()).start();

		//优势：
		//1.多个线程可以公用一个runnable对象
		//2.创建线程不会限制了程序的扩展性，因为是实现接口的方式
	}
}


//写法2：实现Runnable接口
class Worker1 implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello! " + "thread-1");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

class Worker2 implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello! " + "thread-2");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}