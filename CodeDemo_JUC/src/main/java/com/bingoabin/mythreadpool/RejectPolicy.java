package com.bingoabin.mythreadpool;

/**
 * @author bingoabin
 * @date 2021/6/17 17:41
 */
//拒绝策略
public interface RejectPolicy {
	void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}
