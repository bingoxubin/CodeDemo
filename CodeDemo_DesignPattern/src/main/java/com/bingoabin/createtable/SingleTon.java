package com.bingoabin.createtable;

/**
 * @author bingoabin
 * @date 2021/6/14 2:42
 */
public class SingleTon {
	//单例模式：确保一个类只有一个实例，而且自行实例化并向整个系统提供这个实例
	//比如：序列号生成器，web页面的计数器，比如创建一个对象需要消耗很多资源的话，比如访问io或者数据库资源的时候，也可以使用单例模式
}

//饿汉式:在类加载的时候就进行实例化
class SingleTon1 {
	private static SingleTon1 singleTon1 = new SingleTon1();

	private SingleTon1() {
	}

	public static SingleTon1 getInstance() {
		return singleTon1;
	}
}

//懒汉式：类加载的时候不进行实例化，在第一次使用的时候进行实例化
class SingleTon2 {
	private static SingleTon2 singleTon2;

	private SingleTon2() {
	}

	public synchronized static SingleTon2 getInstance() {
		if (singleTon2 == null) {
			singleTon2 = new SingleTon2();
		}
		return singleTon2;
	}
}

//懒汉式:特殊实现双端队列模式，目的是减少加锁的范围   需要volatile修饰静态变量，使得不允许重排序，因为在new SingleTon的时候进行了三步操作：
// 1.分配内存
//2. 初始化对象
//3.指向分配的地址
//若发生重排序，假设A线程执行了1和3，还没有执行2，B线程来到判断NULL，B线程就会直接返回还没初始化的instance了，volatile可以避免重排序
class SingleTon3 {
	private volatile static SingleTon3 singleTon3;

	private SingleTon3() {
	}

	public static SingleTon3 getInstance() {
		if (singleTon3 == null) {
			synchronized (SingleTon.class) {
				if (singleTon3 == null) {
					singleTon3 = new SingleTon3();
				}
			}
		}
		return singleTon3;
	}
}

//一下两种情况还会产生多实例
//1.分布式环境中，多个jvm虚拟机
//2.一个jvm虚拟机，多个类加载器