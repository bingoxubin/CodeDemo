package com.bingoabin.synchronizedtest;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author bingoabin
 * @date 2022/5/29 10:29
 */
public class SynchronizedTest {
	public static void main(String[] args) throws InterruptedException {
		Student student = new Student();
		System.out.println(ClassLayout.parseInstance(student).toPrintable());  //查看对象在内存中的结构

		SynchronizedTest synchronizedTest = new SynchronizedTest();
		synchronizedTest.test4();
	}

	@Test   //偏向锁
	public void test1() throws InterruptedException {
		Thread.sleep(5000);
		Student student = new Student();
		synchronized (student) {
			System.out.println(ClassLayout.parseInstance(student).toPrintable());
		}
	}

	@Test   //轻量级锁
	public void test2() throws InterruptedException {
		Thread.sleep(1000);
		Student student = new Student();
		synchronized (student) {
			System.out.println(ClassLayout.parseInstance(student).toPrintable());
		}
	}

	@Test   //偏向锁 -》 轻量级锁
	public void test3() throws InterruptedException {
		Thread.sleep(5000);
		Student student = new Student();
		Thread thread = new Thread() {
			@Override
			public void run() {
				synchronized (student) {
					System.out.println(ClassLayout.parseInstance(student).toPrintable());
				}
			}
		};
		thread.start();
		thread.join();
		Thread.sleep(5000);
		synchronized (student) {
			System.out.println("main" + ClassLayout.parseInstance(student).toPrintable());
		}
	}

	@Test   //重量级锁
	public void test4() throws InterruptedException {
		Thread.sleep(5000);
		Student student = new Student();
		System.out.println("test");
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				synchronized (student) {
					System.out.println(ClassLayout.parseInstance(student).toPrintable());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				synchronized (student) {
					System.out.println(ClassLayout.parseInstance(student).toPrintable());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread1.start();
		thread2.start();
		Thread.sleep(5000);
	}
}

class Student {
	boolean flag = true;
}
