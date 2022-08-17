package com.bingoabin.threadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bingoabin
 * @date 2022/8/16 23:44
 * @Description: 活锁
 */
public class LifeLockDemo {
	public static final Lock myAccount = new ReentrantLock();
	public static final Lock zhangsanAccount = new ReentrantLock();
	public static void main(String[] args){
	    new Thread(() ->{
			boolean flag = true;
		    while (flag) {
			    try {
				    long time = 1;
				    if (myAccount.tryLock(time, TimeUnit.SECONDS)) {
					    System.out.println("成功锁定我的账户");
					    if (zhangsanAccount.tryLock(time, TimeUnit.SECONDS)) {
						    System.out.println("成功锁定张三的账户");
						    System.out.println("给张三转账");
						    flag = false;
					    } else {
						    myAccount.unlock();
						    System.out.println("放弃锁定我");
					    }
				    } else {
					    System.out.println("放弃锁定我的账户");
				    }
			    } catch (InterruptedException e) {

			    }
		    }
			zhangsanAccount.unlock();
			myAccount.unlock();
	    }).start();

		new Thread(() ->{
			boolean flag = true;
			while (flag) {
				try {
					long time = 1;
					if (zhangsanAccount.tryLock(time, TimeUnit.SECONDS)) {
						System.out.println("成功锁定张三的账户");
						if (myAccount.tryLock(time, TimeUnit.SECONDS)) {
							System.out.println("成功锁定我的账户");
							System.out.println("给我转账");
							flag = false;
						} else {
							zhangsanAccount.unlock();
							System.out.println("放弃锁定张三");
						}
					} else {
						System.out.println("放弃锁定我的账户");
					}
				} catch (InterruptedException e) {

				}
			}
			myAccount.unlock();
			zhangsanAccount.unlock();
		}).start();
	}
}
