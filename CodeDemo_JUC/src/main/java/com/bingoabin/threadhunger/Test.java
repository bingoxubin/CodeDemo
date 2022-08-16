package com.bingoabin.threadhunger;

/**
 * @author bingoabin
 * @date 2022/8/16 23:19
 * @Description: 测试
 */
public class Test {
	public static void main(String[] args){
		MyBlockQueue<Integer> blockQueue = new MyBlockQueue<>();
		new Thread(()-> {
		    while (true) {
			    try {
				    blockQueue.put(1);
				    Thread.sleep(100);
			    } catch (InterruptedException e) {

			    }
		    }
	    }).start();
	}
}
