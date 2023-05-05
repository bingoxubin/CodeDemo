package com.bingoabin.acwingthread;

/**
 * @author bingoabin
 * @date 2023/5/5 20:07
 * @Description:
 */
public class ThreadSynchronized {
	public static void main(String[] args) throws Exception {
		Worker6 worker1 = new Worker6();
		Worker6 worker2 = new Worker6();
		worker1.start();
		worker2.start();

		worker1.join();
		worker2.join();
		System.out.println("Main Thread Finish");
		System.out.println(Worker6.cnt);

		//解决方式一：线程1start后，立马join 等执行完 再执行下一个线程
		//解决方式二：通过lock
		//解决方式三：通过synchronized
	}
}

class Worker6 extends Thread {
	public static int cnt = 0;

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			synchronized (Worker6.class){
				cnt++;
			}
		}
	}
}
