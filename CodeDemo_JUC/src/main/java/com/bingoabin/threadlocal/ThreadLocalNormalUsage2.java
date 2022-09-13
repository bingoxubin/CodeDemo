package com.bingoabin.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bingoabin
 * @date 2022/9/13 10:03
 * @Description: 常用方式
 */
//典型场景1： 每个线程需要一个独享的对象（通常是工具类，典型需要使用的类有SimpleDateFormat和Random）
public class ThreadLocalNormalUsage2 {
	public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			int finalI = i;
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					String date = new ThreadLocalNormalUsage2().date(finalI);
					System.out.println(date);
				}
			});
		}
		threadPool.shutdown();
	}

	public String date(int seconds) {
		//参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
		Date date = new Date(1000 * seconds);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal2.get();
		return dateFormat.format(date);
	}
}

class ThreadSafeFormatter {

	public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 = ThreadLocal
			.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
