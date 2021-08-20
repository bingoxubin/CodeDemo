package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
	public static void main(String[] args) {
		AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
		AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

		//demo01(atomicReference);
		demo02(atomicStampedReference);

	}

	private static void demo02(AtomicStampedReference<Integer> atomicStampedReference) {
		System.out.println("=======ABA问题的解决=======");

		new Thread(() -> {
			//获取当前数据的版本号
			int stamp = atomicStampedReference.getStamp();
			System.out.println("ThreadA====第一次获取的版本号====" + stamp);
			System.out.println("ThreadA====第一次获取的数据====" + atomicStampedReference.getReference());

			//TODO 待完成
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//CAS操作
			boolean result = atomicStampedReference.compareAndSet(
					100,
					2000,
					stamp,
					stamp + 1
			                                                     );
			System.out.println("CAS比较的结果result===" + result);
			System.out.println("ThreadA====第二次获取的版本号====" + atomicStampedReference.getStamp());
			System.out.println("ThreadA====第二次获取的数据====" + atomicStampedReference.getReference());

		}, "ThreadA").start();

		new Thread(() -> {
			//获取当前数据的版本号
			System.out.println("ThreadB====第一次获取的版本号====" + atomicStampedReference.getStamp());
			System.out.println("ThreadB====第一次获取的数据====" + atomicStampedReference.getReference());

			//CAS操作
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			atomicStampedReference.compareAndSet(
					100,
					111,
					atomicStampedReference.getStamp(),
					atomicStampedReference.getStamp() + 1
			                                    );
			System.out.println("ThreadB====第二次获取的版本号====" + atomicStampedReference.getStamp());
			System.out.println("ThreadB====第二次获取的数据====" + atomicStampedReference.getReference());

			//CAS操作
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			atomicStampedReference.compareAndSet(
					111,
					100,
					atomicStampedReference.getStamp(),
					atomicStampedReference.getStamp() + 1
			                                    );
			System.out.println("ThreadB====第三次获取的版本号====" + atomicStampedReference.getStamp());
			System.out.println("ThreadB====第三次获取的数据====" + atomicStampedReference.getReference());

		}, "ThreadB").start();

	}

	private static void demo01(AtomicReference<Integer> atomicReference) {
		System.out.println("=======ABA问题的产生=======");

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//CAS操作
			System.out.println(atomicReference.compareAndSet(100, 2000) + "\t" + atomicReference.get());
		}, "ThreadA").start();

		new Thread(() -> {
			//CAS操作
			System.out.println(atomicReference.compareAndSet(100, 111));
			//CAS操作
			System.out.println(atomicReference.compareAndSet(111, 100));
		}, "ThreadB").start();
	}
}
