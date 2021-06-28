package com.bingoabin.juc.killthread;

/**
 * @author bingoabin
 * @date 2021/6/28 12:29
 */
public class Thread01Stop{
	//Stop方法时被废弃掉的方法，不推荐使用
	// 使用Stop方法，会一直向上传播ThreadDeath异常，从而使得目标线程解锁所有锁住的监视器，即释放掉所有的对象锁。使得之前被锁住的对象得不到同步的处理，因此可能会造成数据不一致的问题。
	// 注释中建议我们取代Stop方法，可以增加一个变量。目标线程周期性地检查这个变量。如果变量在某个时间指示线程终止，则目标线程将以有序的方式从run方法中返回。
	// 当然，如果目标线程长时间进行等待，则可以使用中断的方式来终止线程。
	static class Thread01 extends Thread{
		public void run(){
			for (int i = 0; i < 100000; i++) {
				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread01 thread01 = new Thread01();
		thread01.start();
		Thread.sleep(10);
		thread01.stop();
	}
}
