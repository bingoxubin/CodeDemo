package com.bingoabin.juc1;

/**
 * 单例模式的安全问题
 */
public class SingletonDemo {
	private volatile static SingletonDemo instance = null;

	private SingletonDemo() {
		System.out.println(Thread.currentThread().getName() + "\t SingletonDemo构造方法执行了");
	}

	/**
	 * DCL Double Check Lock 双端检查锁
	 *
	 * @return
	 */
	public static SingletonDemo getInstance() {

		if (instance == null) {
			synchronized (SingletonDemo.class) {
				if (instance == null) {
					instance = new SingletonDemo();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		//多线程操作
		for (int i = 1; i <= 100000; i++) {
			new Thread(() -> {
				SingletonDemo.getInstance();
			}, String.valueOf(i)).start();
		}
	}

}
