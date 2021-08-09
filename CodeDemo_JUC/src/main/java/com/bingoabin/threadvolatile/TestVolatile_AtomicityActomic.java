package com.bingoabin.threadvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 2:37 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestVolatile_AtomicityActomic {
	//通过 Actomic 原子类来实现
	public AtomicInteger inc = new AtomicInteger();

	public void increse() {
		inc.getAndIncrement();
	}

	public static void main(String[] args) {
		final TestVolatile_AtomicityActomic taa = new TestVolatile_AtomicityActomic();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					taa.increse();
					//System.out.println(taa.inc+"===");
				}
			}).start();
		}
		//等待所有的线程执行完毕
		while (Thread.activeCount() > 2) {
			Thread.yield();
		}
		System.out.println(taa.inc);
	}
}
