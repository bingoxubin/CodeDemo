package com.bingoabin.objecttest;

/**
 * @author bingoabin
 * @date 2022/10/26 23:23
 * @Description:
 */
public class Point {
	private int a;
	private int b;

	public Point(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Point{" +
				"a=" + a +
				", b=" + b +
				'}';
	}
}
