package com.bingoabin.objecttest;

/**
 * @author bingoabin
 * @date 2022/10/26 23:25
 * @Description:
 */
public class ColorPoint extends Point {
	private int c;

	public ColorPoint(int a, int b, int c){
		super(a,b);
		this.c = c;
	}

	public ColorPoint() {
		super(3,4);
	}

	public String toString(){
		return String.format("(%d %d %s)",super.getA(),super.getB(),c);
	}

	public static void main(String[] args){
		ColorPoint colorPoint = new ColorPoint();
		System.out.println(colorPoint.getA());
		System.out.println(colorPoint.toString());
	}
}
