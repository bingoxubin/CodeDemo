package com.bingoabin.juc.mythreadpool;

/**
 * @author bingoabin
 * @date 2021/6/17 17:40
 */
//丢弃当前任务
public class DiscardRejectPolicy implements RejectPolicy {
	@Override
	public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
		// do nothing
		System.out.println("discard one task");
	}
}