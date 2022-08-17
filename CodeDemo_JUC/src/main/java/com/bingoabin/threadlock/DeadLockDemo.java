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

	//从三个方面进行避免
	//1.避免在一个线程中获取多把锁，可以通过一把公共锁
	//2.保证所有线程，获取这些锁的顺序是一致的，比如转账的过程中，先锁定id大的  再锁定id小的
	//3.设置超时等待时间
}
