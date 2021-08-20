package com.bingoabin.juc1;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(5);

		System.out.println(atomicInteger.compareAndSet(5, 2000) + "\t 当前数据值:" + atomicInteger.get());
		//修改
		System.out.println(atomicInteger.compareAndSet(5, 1000) + "\t 当前数据值:" + atomicInteger.get());

	}
}
