package com.bingoabin.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author bingoabin
 * @date 2022/5/27 8:55
 */
public class DelayQueueTest {
	//kafka:  https://zhuanlan.zhihu.com/p/365802989
	//delayqueue:  https://blog.csdn.net/c15158032319/article/details/118636233

	//DelayQueue其实采用了装饰器模式，在对PriorityQueue进行包装下增加了延时时间获取元素的功能，其主要特点归纳如下：
	// DelayQueue是一个无界阻塞队列，队列内部使用PriorityQueue来实现。
	// 进入队列的元素必须实现Delayed接口，在创建元素时可以指定多久才能从队列中获取当前元素，只有在延迟期满时才能从中提取元素；
	// 该队列头部是延迟期满后保存时间最长的Delayed元素；
	// 如果没有延迟未过期元素，且队列没有头部，并且poll将返回null；
	// 当一个元素的getDelay（TimeUnit.NANOSECONDS）方法返回一个小于等于0的值时，表示该元素已过期；
	// 无法使用poll或take移除未到期的元素，也不会将这些元素作为正常元素对待；例如：size方法返回到期和未到期元素的计数之和。
	// 此队列不允许使用null元素。
	static BlockingQueue<Delayed> queue = new DelayQueue();
	public static void main(String[] args) throws InterruptedException {
		queue.add(new MyDelay(80000, "第一次添加任务"));
		queue.add(new MyDelay(30000, "第二次添加任务"));
		queue.add(new MyDelay(50000, "第三次添加任务"));

		while (!queue.isEmpty()) {
			Delayed delayed = queue.take();
			System.out.println(delayed);
		}
	}
}

class MyDelay<T> implements Delayed {

	long delayTime; // 延迟时间
	long expire; // 过期时间
	T data;

	public MyDelay(long delayTime, T t) {
		this.delayTime = delayTime;
		// 过期时间 = 当前时间 + 延迟时间
		this.expire = System.currentTimeMillis() + delayTime;
		data = t;
	}

	/**
	 * 剩余时间 = 到期时间 - 当前时间
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	/**
	 * 优先级规则：两个任务比较，时间短的优先执行
	 */
	@Override
	public int compareTo(Delayed o) {
		long f = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
		return (int) f;
	}

	@Override
	public String toString() {
		return "delayTime=" + delayTime +
				", expire=" + expire +
				", data=" + data;
	}
}