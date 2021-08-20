package com.bingoabin.juc1;

import java.util.concurrent.TimeUnit;

/**
 * 回顾Synchronized 同步锁的使用
 */
class Phone {
	public static synchronized void sendEmail() {

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("sendEmail....");
	}

	public synchronized void sendMessage() {
		System.out.println("sendMessage...");
	}

	public void hello() {
		System.out.println("hello...");
	}
}

/**
 * 1. 标准访问, 请问先打印邮件, 还是先打印短信?  --> 邮件
 * 2. 邮件方法暂停3秒钟, 请问先打印邮件, 还是先打印短信?  --> 邮件
 * 3. 新增加一个普通方法hello, 请问先打印邮件, 还是先打印hello?  --> hello
 * 4. 两部手机, 请问先打印邮件 还是先打印短信 -->  短信
 * 5. 两个静态同步方法, 同一部手机, 请问先打印邮件 还是先打印短信? ---> 邮件
 * 6. 两个静态同步方法, 两部手机, 请问先打印邮件 还是先打印短信? ---> 邮件
 * 7. 1个普通同步方法, 1个静态同步方法, 一部手机, 请问先打印邮件 还是先打印短信? ---> 短信
 * 8. 1个普通同步方法, 1个静态同步方法, 二部手机, 请问先打印邮件 还是先打印短信? ---> 短信
 */
public class LockDemo {
	public static void main(String[] args) {
		Phone phone = new Phone();
		Phone phone2 = new Phone();

		new Thread(() -> {
			phone.sendEmail();
		}, "同学A").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(() -> {
			phone2.sendMessage();
		}, "同学B").start();
        /*
        new Thread(()->{
            phone.hello();
        }, "同学C").start();
        */
	}
}
