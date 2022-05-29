package com.bingoabin.deadlocktest;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author bingoabin
 * @date 2022/5/29 11:33
 */
public class DeadLock {
	public static void main(String[] args) {
		DeadLock.deadLock();
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
				if (deadlockedThreads != null) {
					ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(deadlockedThreads);
					System.out.println("检测到的所有的死锁如下：");
					for (ThreadInfo info : threadInfo) {
						System.out.println(info.getThreadId() + ":" + info.getThreadName());
					}
				}
			}
		};
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(thread, 5, 5, TimeUnit.SECONDS);
	}

	public static void deadLock() {
		final String str1 = "足球";
		final String str2 = "篮球";

		Thread thread1 = new Thread() {
			public void run() {
				synchronized (str1) {
					System.out.println("占据了" + str1);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (str2) {
						System.out.println("想要占据" + str2);
					}
				}
			}
		};

		Thread thread2 = new Thread() {
			public void run() {
				synchronized (str2) {
					System.out.println("占据了" + str2);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (str1) {
						System.out.println("想要占据" + str1);
					}
				}
			}
		};

		thread1.start();
		thread2.start();
	}
}
