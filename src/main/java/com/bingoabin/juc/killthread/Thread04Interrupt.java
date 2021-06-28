package com.bingoabin.juc.killthread;

/**
 * @author bingoabin
 * @date 2021/6/28 13:19
 */
public class Thread04Interrupt {
	//什么情况，打断了为什么还能继续输出？
	// 在上面其实已经说过了，interrupt方法用来设置线程的中断状态，如果目标线程正阻塞于wait、sleep等方法时，首先会清除目前线程的中断状态，然后抛出java.lang.InterruptedException异常。
	// 目标线程正在进行第11次循环，进入了sleep操作中，此时收到了主线程的interrupt操作，目标线程拥有了中断状态，则先清除中断状态，然后抛出异常，下一次循环检测Thread.currentThread().isInterrupted()时，isInterrupted依然返回false，从而继续进行循环操作。
	// 那怎么停止当前线程呢，很简单，处理异常时，再次打断即可
	static class Thread04 extends Thread {
		private int i = 0;

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				//业务代码
				i++;
				System.out.println(Thread.currentThread().getName() + ":" + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("线程在sleep期间被打断了");
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread04 thread04 = new Thread04();
		thread04.start();
		Thread.sleep(1);
		thread04.interrupt();
	}
}
