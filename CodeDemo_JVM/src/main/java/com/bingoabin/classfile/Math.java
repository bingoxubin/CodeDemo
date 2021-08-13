package com.bingoabin.classfile;

public class Math {

	/*private final int a = 10;
	private final int b = 10;
	private int c2 = 127;
	private final Integer c3 = 128;
	private int c1 = 20;
	private float c = 11f;
	private float d = 11f;
	private float e = 11f;

	private double dd = 100d;

	private long l = 10L;
	private Long l2 = 110L;
*/
	private int c2 = 127;
	private int c1 = 128;
	private Integer c3 = 129;

	public static void main(String[] args) {
		// int a = 1;
		// int b = 2;
		// int c = (a + b) * 10;

		Integer i = 15;

		Integer i1 = 127;
		Integer i2 = 127;

		System.out.println(i1.equals(i2));

		Integer i3 = 128;
		Integer i4 = 128;

		System.out.println(i3.equals(i4));
		System.out.println(i3 == i4);//TODO 解读

		ClassLoader cl;

	}
}
