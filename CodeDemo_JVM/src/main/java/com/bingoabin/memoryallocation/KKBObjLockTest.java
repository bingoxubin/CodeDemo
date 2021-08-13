package com.bingoabin.memoryallocation;

import org.openjdk.jol.info.ClassLayout;

public class KKBObjLockTest {
	public static void main(String[] args) {
		Object o = new Object();
		System.out.println("new Object:" + ClassLayout.parseInstance(o).toPrintable());
	}
}
