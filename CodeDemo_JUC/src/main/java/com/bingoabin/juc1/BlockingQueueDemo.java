package com.bingoabin.juc1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列的演示
 */
public class BlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

		//addAndRemove(blockingQueue);

		// offerAndPoll(blockingQueue);

		// putAndTake(blockingQueue);

		outOfTime(blockingQueue);
	}

	/**
	 * 超时的方式演示
	 *
	 * @param blockingQueue
	 */
	private static void outOfTime(BlockingQueue<String> blockingQueue) throws InterruptedException {
		System.out.println(blockingQueue.offer("AA", 4, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("BB", 4, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("CC", 4, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("DD", 4, TimeUnit.SECONDS));

		//取出队列元素
		System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));
		System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));
		System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));
		System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));
	}

	/**
	 * 阻塞形式的方式演示
	 *
	 * @param blockingQueue
	 */
	private static void putAndTake(BlockingQueue<String> blockingQueue) throws InterruptedException {
		blockingQueue.put("aaa");
		blockingQueue.put("bbb");
		blockingQueue.put("ccc");
		//blockingQueue.put("ddd");

		//取出队列元素
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
	}

	/**
	 * 返回布尔值的方法演示
	 *
	 * @param blockingQueue
	 */
	private static void offerAndPoll(BlockingQueue<String> blockingQueue) {
		System.out.println(blockingQueue.offer("aa"));
		System.out.println(blockingQueue.offer("bb"));
		System.out.println(blockingQueue.offer("cc"));
		System.out.println(blockingQueue.offer("dd"));

		//查看队首元素
		//System.out.println(blockingQueue.peek());

		//取出队列中的元素
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
	}

	/**
	 * 抛出异常的方法演示
	 *
	 * @param blockingQueue
	 */
	private static void addAndRemove(BlockingQueue<String> blockingQueue) {
		System.out.println(blockingQueue.add("a"));
		System.out.println(blockingQueue.add("b"));
		System.out.println(blockingQueue.add("c"));
		//System.out.println(blockingQueue.add("d"));

		//查看队首元素
		// System.out.println(blockingQueue.element());

		//取出队列元素
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());

	}
}
