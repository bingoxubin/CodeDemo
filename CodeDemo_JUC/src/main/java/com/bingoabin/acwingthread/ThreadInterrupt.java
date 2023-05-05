package com.bingoabin.acwingthread;

/**
 * @author bingoabin
 * @date 2023/5/5 19:10
 * @Description:
 */
public class ThreadInterrupt {
	public static void main(String[] args) throws Exception{
		Worker4 worker1 = new Worker4();
		Worker4 worker2 = new Worker4();
		worker1.setName("worker_1");
		worker1.setName("worker_2");
		worker1.start();
		worker2.start();
		worker1.join(5000);
		//等5秒钟给线程发送一个中断,线程执行过程中会获取异常，然后进行输出信息，退出（注意：如果不break，还是会执行）
		//只能中断 wait 或者 sleep的线程
		worker1.interrupt();
		System.out.println("Main Thread finished");
	}
}

class Worker4 extends Thread{
	@Override
	public void run() {
		for(int i = 0;i<10;i++){
			System.out.println("Hello! " + this.getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				break;
				// e.printStackTrace();
			}
		}
	}
}
