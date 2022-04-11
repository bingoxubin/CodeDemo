package com.bingoabin.killthread;

/**
 * @author bingoabin
 * @date 2021/6/28 13:09
 */
public class Thread02CheckValue {
	//通过指定一个条件变量，外部线程（比如是主线程）可以控制该变量，内部线程（比如子线程）在内部循环检查该变量。为了保证条件变量在内存中的可见性，我们使用volatile修饰它。有关volatile是如何保证可见性的
	//可以看得出，如果我们的业务要求在每一次循环结束后去检查条件变量，那么以上的写法是没有问题的。
	// 但是，以上方式的即时性不高，如果我们的业务代码有等待操作，比如sleep与wait操作，那么如何在这些操作中就终止线程呢？
	static class Thread02 extends Thread {
		private int i = 0;
		private volatile boolean flag = false;

		public void run() {
			while (!flag) {
				i++;
				System.out.println(i);
			}
		}

		public void finish() {
			flag = true;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread02 thread02 = new Thread02();
		thread02.start();
		Thread.sleep(1);
		thread02.finish();
	}
}
