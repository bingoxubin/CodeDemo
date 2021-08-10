package com.bingoabin.memoryallocation;

import sun.awt.geom.AreaOp;

/**
 * 内存分配担保案例
 * 
 * @author 灭霸詹
 *
 */
public class MemoryAllocationGuarantee {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		memoryAllocation();

	}

	public static void memoryAllocation() {

		byte[] allocation1, allocation2, allocation3, allocation4;

		allocation1 = new byte[2 * _1MB];//2M
		allocation2 = new byte[2 * _1MB];//2M
		allocation3 = new byte[2 * _1MB];//2M
		allocation4 = new byte[3 * _1MB];//4M
//		allocation4 = new byte[5 * _1MB];//4M
//		allocation4 = new byte[3 * _1MB];//4M

		System.out.println("完毕");
	}
}




















