package com.bingoabin.juc1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是，如果有一个线程想去写共享资料，就不应该再有其他线程可以对该资源进行读或写
 * 小总结：
 * 读-读 能共存
 * 读-写 不能共存
 * 写-写 不能共存
 */
class Cache {
	private Map<String, Object> map = new ConcurrentHashMap<>();

	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * 记录哪个线程来 签字
	 *
	 * @param key   线程名
	 * @param value 签字内容
	 */
	public void put(String key, Object value) {
		readWriteLock.writeLock().lock();
		try {
			System.out.println(key + "====正在签字: " + value);
			//耗时操作
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println("===签字完成===");
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	/**
	 * 获取指定线程 对应的value
	 *
	 * @param key 线程名
	 * @return 签字内容
	 */
	public Object get(String key) {

		Object result = null;
		readWriteLock.readLock().lock();
		try {
			System.out.println(key + "====正在看大家的签字:");
			//耗时操作
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result = map.get(key);
			System.out.println(key + "===看完了===");
		} finally {
			readWriteLock.readLock().unlock();
		}
		return result;
	}
}

public class ReadWriteLockDemo {
	public static void main(String[] args) {
		Cache cache = new Cache();

		//写
		for (int i = 1; i <= 5; i++) {
			final int temp = i;
			new Thread(() -> {
				cache.put("线程名" + temp, "数据" + temp);
			}, String.valueOf(i)).start();
		}

		//读
		for (int i = 1; i <= 5; i++) {
			final int temp = i;
			new Thread(() -> {
				cache.get("线程名" + temp);
			}, String.valueOf(i)).start();
		}
	}
}
