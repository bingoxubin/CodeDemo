package com.bingoabin.threadvolatile;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 2:38 下午
 * @CopyRight: sankuai.com
 * @Description: 通过synchronized 来实现线程安全
 */
public class TestVolatile_AtomicitySynchronized {
	//通过synchronized 同步方法 来解决
	public int inc = 0;

	public synchronized void increase() {
		inc++;
	}

	public static void main(String[] args) {
		TestVolatile_AtomicitySynchronized tas = new TestVolatile_AtomicitySynchronized();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 10000; j++) {
						tas.increase();
					}
				}
			}.start();
		}

		while (Thread.activeCount() > 2) {
			Thread.yield();
		}
		System.out.println(tas.inc);
	}
}
