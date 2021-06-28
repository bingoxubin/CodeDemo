package com.bingoabin.juc.killthread;

/**
 * @author bingoabin
 * @date 2021/6/28 13:23
 */
public class Thread05DoubleInterrupt {
	//使用条件变量+中断的方式是终止线程比较优雅的方式，具有一定的即时性。中断仅仅是一种协作机制，是需要被中断的线程自己去处理中断的。
	static class Thread05 extends Thread {
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
					this.interrupt();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread05 thread05 = new Thread05();
		thread05.start();
		//保证子线程进入运行状态，避免还没运行就被终止
		Thread.sleep(1000);
		//停止子线程
		thread05.interrupt();
	}
}
