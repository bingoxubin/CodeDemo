package com.bingoabin.threadvolatile;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 2:16 下午
 * @CopyRight: sankuai.com
 * @Description: volatile无法保证原子性实例
 */
public class TestVolatile_NoAtomicity {
	public volatile int inc = 0;

	public void increse() {
		inc++;
	}

	//Volatile 不保证原子性
	@Test
	public static void main(String[] args) {
		final TestVolatile_NoAtomicity tno = new TestVolatile_NoAtomicity();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 10000; j++) {
						tno.increse();
						//System.out.println(tno.inc+"===");
					}
				}
			}.start();
		}
		//保证前面的线程都执行完
		while (Thread.activeCount() > 2) {
			Thread.yield();
		}
		System.out.println(tno.inc);
	}
}
