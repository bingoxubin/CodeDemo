package com.bingoabin.mythreadpool;

import com.bingoabin.juc.mythreadpool.MyThreadPoolExecutor;

/**
 * @author bingoabin
 * @date 2021/6/17 17:41
 */
//拒绝策略
public interface RejectPolicy {
	void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}
