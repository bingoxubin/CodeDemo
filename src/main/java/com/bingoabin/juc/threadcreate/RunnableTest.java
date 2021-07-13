package com.bingoabin.juc.threadcreate;

/**
 * @author xubin03
 * @date 2021/5/25 3:49 下午
 */

//实现Runnable接口
// 创建多线程的方式二：实现Runnable接口
// 1. 创建一个实现了Runnable接口的类
// 2. 实现类去实现Runnable中的抽象方法：run()
// 3. 创建实现类的对象
// 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
// 5. 通过Thread类的对象调用start()   通过Thread类的对象调用start():① 启动线程 ②调用当前线程的run()-->调用了Runnable类型的target的run()
public class RunnableTest {
	public static void main(String[] args) {
		//方式一:
		Runnable1 runnable1 = new Runnable1();
		Thread thread = new Thread(runnable1);
		thread.start();

		//方式二:
		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}, "threadname1");

		//方式三：
		new Thread(() -> {

		}, "thread").start();
	}

	static class Runnable1 implements Runnable {
		@Override
		public void run() {

		}
	}
}
