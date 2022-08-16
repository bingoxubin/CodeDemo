package com.bingoabin.threadlock;

/**
 * @author bingoabin
 * @date 2022/8/16 23:34
 * @Description: 死锁
 */
public class DeadLockDemo {
	public static final Object myAccount = new Object();
	public static final Object zhangsanAccount = new Object();
	public static void main (String[]args){
		//转账给张三
		new Thread(() ->{
			synchronized (myAccount) {
				System.out.println("判断余额");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

				}
				synchronized (zhangsanAccount) {
					System.out.println("我的账户扣款");
					System.out.println("张三存款");
				}
			}
		}).start();

		//张三转给我
		new Thread(() ->{
			synchronized (zhangsanAccount) {
				System.out.println("判断余额");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

				}
				synchronized (myAccount) {
					System.out.println("张三账户扣款");
					System.out.println("我的账户存款");
				}
			}
		}).start();
	}
}
