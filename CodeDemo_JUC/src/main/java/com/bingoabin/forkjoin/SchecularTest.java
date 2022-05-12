package com.bingoabin.forkjoin;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author bingoabin
 * @date 2022/5/2 23:08
 */
public class SchecularTest {
	public static void main(String[] args) throws Exception {
		ForkJoinPool pool1 = new ForkJoinPool();
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 5; i++) {
			Future<Integer> result = pool.schedule(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int num = new Random().nextInt(100);//生成随机数
					System.out.println(Thread.currentThread().getName() + " : " + num);
					return num;
				}

			}, 1, TimeUnit.SECONDS);
			System.out.println(result.get());
		}
		pool.shutdown();
	}
}
