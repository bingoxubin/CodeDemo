package com.bingoabin.threadprincipal;

import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/3/3 9:57 上午
 */
public class RunnableTaskQueue {
	private final LinkedList<Runnable> tasks = new LinkedList<>();

	public Runnable getTask() throws InterruptedException {
		synchronized (tasks) {
			while (tasks.isEmpty()) {
				System.out.println(Thread.currentThread().getName() + " says task queue is empty. i will wait");
				tasks.wait();
			}
			return tasks.removeFirst();
		}
	}

	public void addTask(Runnable runnable) {
		synchronized (tasks) {
			tasks.add(runnable);
			tasks.notifyAll();
		}
	}
}
