package com.bingoabin.threadhunger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author bingoabin
 * @date 2022/8/16 23:17
 * @Description: 阻塞队列
 */
public class MyBlockQueue<T> {
	private Queue<T> queue = new LinkedList<>();

	public synchronized void put(T obj){
		queue.add(obj);
		notify();
	}

	public synchronized T get(){
		while (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		return queue.remove();
	}
}
