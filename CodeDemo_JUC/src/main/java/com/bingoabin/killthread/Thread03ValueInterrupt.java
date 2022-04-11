package com.bingoabin.killthread;

/**
 * @author bingoabin
 * @date 2021/6/28 13:17
 */
public class Thread03ValueInterrupt {
	//interrupt方法用来设置线程的中断状态，如果目标线程正阻塞于wait、sleep等方法时，首先会清除目前线程的中断状态，然后抛出java.lang.InterruptedException异常。
	// 可以看得出，子线程确实被终止掉了。
	// 能不能使用Thread.isInterrupted()来代替条件变量呢？
	static class Thread03 extends Thread {
		private int i = 0;
		private volatile boolean flag = false;

		public void run() {
			while (!flag) {
				i++;
				System.out.println(i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("线程在sleep期间被打断了");
					e.printStackTrace();
				}
			}
		}

		public void finish() {
			flag = true;
			this.interrupt();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread03 thread03 = new Thread03();
		thread03.start();
		Thread.sleep(1);
		thread03.finish();
	}
}
