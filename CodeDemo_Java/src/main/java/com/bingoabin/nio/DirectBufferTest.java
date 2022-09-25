package com.bingoabin.nio;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author bingoabin
 * @date 2022/9/25 20:09
 * @Description: 堆外内存
 */
public class DirectBufferTest {
	public static void main(String[] args) {
		Unsafe unsafe = getUnsafe();
		//分配10个字节的内存，返回值为内存基础地址
		long address = unsafe.allocateMemory(10);

		//传入基础地址，长度10，byte-0，初始化堆外内存
		unsafe.setMemory(address, 10L, (byte) 0);

		//传入内存地址位置设置byte值
		unsafe.putByte(address, (byte) 1);
		unsafe.putByte(address + 1, (byte) 2);
		unsafe.putByte(address + 2, (byte) 3);

		//根据内存地址获取byte值
		System.out.println(unsafe.getByte(address));
		System.out.println(unsafe.getByte(address + 1));
		System.out.println(unsafe.getByte(address + 2));

		//释放内存
		unsafe.freeMemory(address);
	}

	//反射拿到Unsafe对象
	private static Unsafe getUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (Exception e) {
			return null;
		}
	}
}
