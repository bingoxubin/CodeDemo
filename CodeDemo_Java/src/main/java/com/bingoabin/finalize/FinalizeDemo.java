package com.bingoabin.finalize;

/**
 * @author bingoabin
 * @date 2022/8/16 22:47
 * @Description: OOM的情况
 */
public class FinalizeDemo {
	byte[] data = new byte[1024];

	public static void main(String[] args) {
		while (true) {
			new FinalizeDemo();
		}
	}

	//回收的成本变高了
	@Override
	protected void finalize() throws Throwable{
		System.out.println("");
	}
}
