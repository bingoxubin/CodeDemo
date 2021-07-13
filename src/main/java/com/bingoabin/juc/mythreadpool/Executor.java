package com.bingoabin.juc.mythreadpool;

/**
 * @author bingoabin
 * @date 2021/6/17 17:42
 */
public interface Executor {
	void execute(Runnable command);
}
