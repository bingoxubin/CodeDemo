package com.bingoabin.threadvolatile;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 12:13 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestVolatile {
	//volatile 可见性
	public static volatile boolean flag = false;

	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println("修改flag的线程开始");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			flag = true;
			System.out.println("修改flag的线程结束");
		}).start();

		while (true) {
			if (flag) {
				System.out.println("flag修改了，退出循环");
				break;
			}
		}
	}
}
