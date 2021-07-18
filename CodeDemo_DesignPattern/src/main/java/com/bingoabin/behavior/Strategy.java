package com.bingoabin.behavior;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author bingoabin
 * @date 2021/6/15 1:41
 */
public class Strategy {
	//策略模式：定义一组算法，将每个算法都封装起来，并且使他们之间可以互换。策略模式让算法独立于使用它的客户而变化，也称为政策模式 -- 跟状态模式很像
	//比如 洗衣机的 标准 浸洗 快洗 大物等

	//例如ThreadPoolExecutor构造方法中的拒绝策略
	public static void main(String[] args) {
		new ThreadPoolExecutor(2, 5, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.AbortPolicy());
	}
}

class StrategyPattern {

}
