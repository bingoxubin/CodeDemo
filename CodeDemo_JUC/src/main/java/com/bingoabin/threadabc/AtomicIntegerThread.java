package com.bingoabin.threadabc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bingoabin
 * @date 2022/4/19 23:36
 */
public class AtomicIntegerThread implements Runnable {
	private final AtomicInteger atomicInteger;
	private final String name;

	private static final Integer max_count = 15;
	private static final String[] buffer = {"A", "B", "C"};

	public AtomicIntegerThread(String name, AtomicInteger atomicInteger) {
		this.atomicInteger = atomicInteger;
		this.name = name;
	}

	@Override
	public void run() {
		while (atomicInteger.get() < max_count) {
			if (name.equals(buffer[atomicInteger.get() % 3])) {
				System.out.print(name);
				atomicInteger.getAndIncrement();
			}
		}
	}

	public static void main(String[] args) {
		AtomicInteger num = new AtomicInteger(0);

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		//ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 20, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executorService.execute(new AtomicIntegerThread("A", num));
		executorService.execute(new AtomicIntegerThread("B", num));
		executorService.execute(new AtomicIntegerThread("C", num));
		executorService.shutdown();
	}
}
